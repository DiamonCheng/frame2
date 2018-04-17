package com.dc.frame2.view;

import com.dc.frame2.util.MapBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * <p>Descriptions...
 *
 * @author Defferson.Cheng
 * @date 2018/4/17.
 */
public class Test {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(Test.class, "/templates");
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("test/test.html.ftl", (Locale) null);
        template.process(
                MapBuilder.<String, Object>hashMap()
                        .put("who",
                                MapBuilder.<String, Object>hashMap()
                                        .put("im", " DCCCCCC   ")
                                        .build())
                        .build(), new OutputStreamWriter(System.out));
        
    }
}
