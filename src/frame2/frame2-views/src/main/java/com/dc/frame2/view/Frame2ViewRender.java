package com.dc.frame2.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/19.
 */
public interface Frame2ViewRender {
    void render(Frame2View frame2View, Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
    
    Class<? extends Frame2View> supportType();
}
