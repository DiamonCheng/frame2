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
    private static final String TEMPLATE_NAME="/test/test.html.ftl";
    private  Configuration configuration;
    private String name;
    @Override
    public void render(Locale locale, OutputStreamWriter writer,Object exModule) throws IOException, TemplateException {
        Template template=configuration.getTemplate(TEMPLATE_NAME,locale);
        if (exModule instanceof SimpleHash){
            ((SimpleHash) exModule).put(ROOT_PARAM_NAME,this);
            template.process(exModule,writer);
        }else {
            template.process(MapBuilder.<String, Object>hashMap()
                                     .put(ROOT_PARAM_NAME,this)
                                     .build(), writer);
        }
    }
    
    public SimpleContentView setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }
    
    public SimpleContentView setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getName() {
        return name;
    }
    
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
}
