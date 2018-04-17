package com.dc.frame2.view.view.freemarker;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.Frame2View;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/17.
 */
public class SimpleContentView implements Frame2View {
    private static final String TEMPLATE_NAME="test/test.html.ftl";
    private  Configuration configuration;
    private String name;
    @Override
    public void render(Locale locale, OutputStreamWriter writer) throws IOException, TemplateException {
        Template template=configuration.getTemplate(TEMPLATE_NAME,locale);
        template.process(MapBuilder.<String, Object>hashMap()
                                 .put("who",
                                         MapBuilder.<String, Object>hashMap()
                                                 .put("im", name)
                                                 .build())
                                 .build(),writer);
    }
    
    public SimpleContentView setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }
    
    public SimpleContentView setName(String name) {
        this.name = name;
        return this;
    }
}
