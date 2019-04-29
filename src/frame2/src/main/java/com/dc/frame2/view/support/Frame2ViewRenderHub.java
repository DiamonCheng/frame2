package com.dc.frame2.view.support;

import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.Frame2ViewRender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/24.
 */
public class Frame2ViewRenderHub implements Frame2ViewRender {
    private List<Frame2ViewRender> renderList = new ArrayList<>();
    
    @Override
    public void render(Frame2View frame2View, Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        for (Frame2ViewRender frame2ViewRender : renderList) {
            if (frame2ViewRender.supportType().isAssignableFrom(frame2View.getClass())) {
                frame2ViewRender.render(frame2View, model, request, response);
                return;
            }
        }
        throw new IllegalStateException(String.format("For view %s cannot find a support view render.", frame2View.getClass()));
    }
    
    @Override
    public Class<? extends Frame2View> supportType() {
        return Frame2View.class;
    }
    
    public Frame2ViewRenderHub addFrame2ViewRender(Frame2ViewRender frame2ViewRender) {
        renderList.add(frame2ViewRender);
        return this;
    }
}
