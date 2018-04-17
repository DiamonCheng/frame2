package com.dc.frame2.view.support;

import com.dc.frame2.view.Frame2View;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import java.awt.*;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
public class Frame2ViewHandler implements HandlerMethodReturnValueHandler {
    private static final String CONTENT_TYPE="text/html";
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
                frame2View.render(response.getLocale(),new OutputStreamWriter(response.getOutputStream(),response.getCharacterEncoding()));
            }
        });
        
    }
}
