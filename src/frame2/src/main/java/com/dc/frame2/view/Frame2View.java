package com.dc.frame2.view;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
public interface Frame2View {
    String getTemplateName();
    Map<String,Object> getParam();
}
