package com.dc.frame2.view.support;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.engine.freemarker.FreeMarkerConfigurationManager;
import freemarker.ext.jsp.TaglibFactory;
import freemarker.ext.servlet.*;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.ObjectWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.servlet.view.AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
public class Frame2ViewHandler implements HandlerMethodReturnValueHandler,ServletContextAware {
    private static final String CONTENT_TYPE="text/html";
    
    private ServletContext servletContext;
    private ServletContextHashModel servletContextHashModel;
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Frame2View.class.isAssignableFrom(returnType.getParameterType());
    }
    
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        if (returnValue == null) {
            mavContainer.setRequestHandled(true);
            return;
        }
        Frame2View frame2View= (Frame2View) returnValue;
        mavContainer.setView(new View() {
    
            @Override
            public String getContentType() {
                return CONTENT_TYPE;
            }
    
            @Override
            public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                Map<String,Object> module2=new HashMap<>(3);
                for (Map.Entry<String, ?> entry : model.entrySet()) {
                    String modelName = entry.getKey();
                    Object modelValue = entry.getValue();
                    
                    module2.put(modelName,modelValue);
                    
                    if (modelValue != null) {
                        request.setAttribute(modelName, modelValue);
                    }
                    else {
                        request.removeAttribute(modelName);
                    }
                }
    
                RequestContext requestContext=new RequestContext(request, response, getServletContext(),module2);
                AllHttpScopesHashModel fmModel = new AllHttpScopesHashModel(getObjectWrapper(), getServletContext(), request);
                fmModel.put(SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE,requestContext);
                fmModel.put(FreemarkerServlet.KEY_APPLICATION, Frame2ViewHandler.this.servletContextHashModel);
                fmModel.put(FreemarkerServlet.KEY_SESSION, buildSessionModel(request, response));
                fmModel.put(FreemarkerServlet.KEY_REQUEST, new HttpRequestHashModel(request, response, getObjectWrapper()));
                fmModel.put(FreemarkerServlet.KEY_REQUEST_PARAMETERS, new HttpRequestParametersHashModel(request));
                fmModel.putAll(model);
                frame2View.render(response.getLocale(),new OutputStreamWriter(response.getOutputStream(),response.getCharacterEncoding()),fmModel);
            }
        });
        
    }
    private ObjectWrapper getObjectWrapper() {
        ObjectWrapper ow = FreeMarkerConfigurationManager.getInstance().getConfiguration().getObjectWrapper();
        return (ow != null ? ow :
                        new DefaultObjectWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).build());
    }
    private HttpSessionHashModel buildSessionModel(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return new HttpSessionHashModel(session, getObjectWrapper());
        }
        else {
            return new HttpSessionHashModel(null, request, response, getObjectWrapper());
        }
    }
    
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
    
    public ServletContext getServletContext() {
        return servletContext;
    }
    
    @PostConstruct
    public void init(){
        GenericServlet servlet = new GenericServlet() {
            @Override public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {}
        };
        try {
            servlet.init(new ServletConfig() {
                @Override
                public String getServletName() {
                    return "Frame2ViewServlet";
                }
        
                @Override
                public ServletContext getServletContext() {
                    return Frame2ViewHandler.this.getServletContext();
                }
        
                @Override
                public String getInitParameter(String paramName) {
                    return null;
                }
        
                @Override
                public Enumeration<String> getInitParameterNames() {
                    return Collections.enumeration(Collections.<String>emptySet());
                }
            });
        }
        catch (ServletException ex) {
            throw new BeanInitializationException("Initialization of GenericServlet adapter failed", ex);
        }
        this.servletContextHashModel = new ServletContextHashModel(servlet, getObjectWrapper());
    }
}
