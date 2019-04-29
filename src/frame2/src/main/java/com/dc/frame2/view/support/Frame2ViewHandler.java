package com.dc.frame2.view.support;

import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.Frame2ViewRender;
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
    private Frame2ViewRender frame2ViewRender;
    private Frame2ViewConfiguration frame2ViewConfiguration;
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return frame2ViewRender.supportType().isAssignableFrom(returnType.getParameterType());
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
                return frame2ViewConfiguration.getContentType();
            }
            
            @Override
            public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                response.setCharacterEncoding(frame2ViewConfiguration.getCharset());
                response.addHeader("Content-Type", frame2ViewConfiguration.getContentType() + ";charset=" + frame2ViewConfiguration.getCharset());
                frame2ViewRender.render(frame2View, model, request, response);
            }
        });
    }
    
    
    public Frame2ViewHandler setFrame2ViewRender(Frame2ViewRender frame2ViewRender) {
        this.frame2ViewRender = frame2ViewRender;
        return this;
    }
    
    public Frame2ViewHandler setFrame2ViewConfiguration(Frame2ViewConfiguration frame2ViewConfiguration) {
        this.frame2ViewConfiguration = frame2ViewConfiguration;
        return this;
    }
}
