package com.dc.frame2.view.view.freemarker;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.Frame2View;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/19.
 */
public interface FreemarkerView extends Frame2View{
    
    String getTemplateName();
    
}
