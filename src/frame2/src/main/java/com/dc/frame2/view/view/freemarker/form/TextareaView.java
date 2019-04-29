package com.dc.frame2.view.view.freemarker.form;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/11.
 */
public class TextareaView extends AbstractInput<TextareaView> implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/form/textarea.html.ftl";
    
    private String value;
    private String placeholder;
    
    public String getValue() {
        return value;
    }
    
    public TextareaView setValue(String value) {
        this.value = value;
        return this;
    }
    
    public String getPlaceholder() {
        return placeholder;
    }
    
    public TextareaView setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

//    private
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap().put("textarea",
                MapBuilder.dataMap()
                        .put("id", getId())
                        .put("name", getName())
                        .put("title", getTitle())
                        .put("value", value)
                        .put("placeholder", placeholder)
                        .put("attrs", getAttrs())
                        .put("classes", getClasses())
                        .build()
        ).build();
    }
}
