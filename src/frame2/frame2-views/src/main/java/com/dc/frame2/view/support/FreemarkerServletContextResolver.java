package com.dc.frame2.view.support;

import freemarker.ext.servlet.*;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.support.RequestContext;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.servlet.view.AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/20.
 */
public class FreemarkerServletContextResolver implements ServletContextAware, Frame2ServletContextResolver {
    private Configuration configuration;
    private ServletContext servletContext;
    private ObjectWrapper objectWrapper;
    private ServletContextHashModel servletContextHashModel;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
    
    public Frame2ServletContextResolver setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }
    
    @Override
    public SimpleHash buildContextModule(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response){
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
    
        RequestContext requestContext=new RequestContext(request, response, servletContext,module2);
        AllHttpScopesHashModel fmModel = new AllHttpScopesHashModel(objectWrapper,servletContext, request);
        fmModel.put(SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE,requestContext);
        fmModel.put(FreemarkerServlet.KEY_APPLICATION, servletContextHashModel);
        fmModel.put(FreemarkerServlet.KEY_SESSION, buildSessionModel(request, response));
        fmModel.put(FreemarkerServlet.KEY_REQUEST, new HttpRequestHashModel(request, response, objectWrapper));
        fmModel.put(FreemarkerServlet.KEY_REQUEST_PARAMETERS, new HttpRequestParametersHashModel(request));
        
        return fmModel;
    }
    
    @PostConstruct
    public void init(){
        objectWrapper = configuration.getObjectWrapper()==null?
                              new DefaultObjectWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).build()
                              : configuration.getObjectWrapper();
        
        
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
                    return FreemarkerServletContextResolver.this.servletContext;
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
        this.servletContextHashModel = new ServletContextHashModel(servlet, objectWrapper);
    }
    
    private HttpSessionHashModel buildSessionModel(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return new HttpSessionHashModel(session, objectWrapper);
        }
        else {
            return new HttpSessionHashModel(null, request, response, objectWrapper);
        }
    }
}
