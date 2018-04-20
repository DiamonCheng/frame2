package com.dc.frame2.view.support;

import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.engine.freemarker.FreemarkerViewRender;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
public class Frame2ViewHandler implements HandlerMethodReturnValueHandler {
    private static final String CONTENT_TYPE = "text/html";
    private static final String RENDER_NAME="RENDER";
    
    private Frame2ServletContextResolver frame2ServletContextResolver;
    private Frame2ViewConfiguration frame2ViewConfiguration;
    private Configuration configuration;
    
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
        Frame2View frame2View = (Frame2View) returnValue;
        mavContainer.setView(new View() {
            
            @Override
            public String getContentType() {
                return CONTENT_TYPE;
            }
            
            @Override
            public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                SimpleHash fmModel = frame2ServletContextResolver.buildContextModule(model, request, response);
                response.setCharacterEncoding(frame2ViewConfiguration.getCharset());
                FreemarkerViewRender render=new FreemarkerViewRender()
                        .setConfiguration(configuration)
                        .setModuleContext(fmModel)
                        .setWriter(response.getWriter());
                fmModel.put(RENDER_NAME,render);
    
                render.render(frame2View);
            }
        });
    }
    
    public Frame2ViewHandler setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }
    
    public Frame2ViewHandler setFrame2ServletContextResolver(Frame2ServletContextResolver frame2ServletContextResolver) {
        this.frame2ServletContextResolver = frame2ServletContextResolver;
        return this;
    }
    
    public Frame2ViewHandler setFrame2ViewConfiguration(Frame2ViewConfiguration frame2ViewConfiguration) {
        this.frame2ViewConfiguration = frame2ViewConfiguration;
        return this;
    }
}
