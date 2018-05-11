package com.dc.frame2.view.view.freemarker.form;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/11.
 */
public class SelectView extends AbstractInput<SelectView> implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/form/select.html.ftl";
    
    public static class Option {
        private String text = "";
        private String value = "";
        private boolean selected = false;
        private Map<String, String> attrs = new HashMap<>(3);
        
        public String getText() {
            return text;
        }
        
        public Option setText(String text) {
            this.text = text;
            return this;
        }
        
        public String getValue() {
            return value;
        }
        
        public Option setValue(String value) {
            this.value = value;
            return this;
        }
        
        public boolean isSelected() {
            return selected;
        }
        
        public Option setSelected(boolean selected) {
            this.selected = selected;
            return this;
        }
        
        public Map<String, String> getAttrs() {
            return attrs;
        }
        
        public Option setAttrs(Map<String, String> attrs) {
            this.attrs = attrs;
            return this;
        }
    }
    
    private List<Option> options = new ArrayList<>(3);
    
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap().put("select",
                MapBuilder.dataMap()
                        .put("id", getId())
                        .put("name", getName())
                        .put("title", getTitle())
                        .put("classes", getClasses())
                        .put("attrs", getAttrs())
                        .put("options", options)
                        .build()
        ).build();
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
}
