package com.dc.frame2.view.view.freemarker;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.Frame2View;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
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
public class SimpleContentView implements Frame2View {
    private static final String TEMPLATE_NAME="test/test.html.ftl";
    private String name;
    
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.<String,Object>hashMap().put("name",name).build();
    }
    
    public SimpleContentView setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getName() {
        return name;
    }
}
