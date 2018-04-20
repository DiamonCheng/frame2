package com.dc.frame2.view;

import com.dc.frame2.view.Frame2View;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/19.
 */
public interface Frame2ViewRender {
    void render(Frame2View frame2View) throws IOException, TemplateException;
}
