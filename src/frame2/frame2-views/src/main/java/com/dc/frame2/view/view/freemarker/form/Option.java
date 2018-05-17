package com.dc.frame2.view.view.freemarker.form;

import java.util.HashMap;
import java.util.Map;

public class Option {
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