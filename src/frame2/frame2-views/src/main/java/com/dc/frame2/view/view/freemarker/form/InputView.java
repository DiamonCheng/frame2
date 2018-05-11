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
public class InputView extends AbstractInput<InputView> implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/form/input.html.ftl";
    
    private static class Type {
        public static final String HIDDEN = "hidden";
        public static final String TEXT = "text";
        public static final String PASSWORD = "password";
        public static final String NUMBER = "number";
        /**
         * value 不支持多语言 不推荐使用
         */
        @Deprecated
        public static final String SUBMIT = "submit";
        /**
         * value 不支持多语言 不推荐使用
         */
        @Deprecated
        public static final String RESET = "reset";
    }
    
    private String type = Type.TEXT;
    private String value;
    private String placeholder;
    
    public String getType() {
        return type;
    }
    
    public InputView setType(String type) {
        this.type = type;
        return this;
    }
    
    public String getValue() {
        return value;
    }
    
    public InputView setValue(String value) {
        this.value = value;
        return this;
    }
    
    public String getPlaceholder() {
        return placeholder;
    }
    
    public InputView setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap().put("input",
                MapBuilder.dataMap()
                        .put("id", getId())
                        .put("type", type)
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
