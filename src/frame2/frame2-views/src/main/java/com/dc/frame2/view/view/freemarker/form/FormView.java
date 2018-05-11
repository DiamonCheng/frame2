package com.dc.frame2.view.view.freemarker.form;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/6.
 */
public class FormView implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/form/form.html.ftl";
    private String id;
    private String action = "";
    private String method = "GET";
    private List<Frame2View> contents = new ArrayList<>();
    
    public FormView setAction(String action) {
        this.action = action;
        return this;
    }
    
    public FormView setId(String id) {
        this.id = id;
        return this;
    }
    
    public FormView setMethod(String method) {
        this.method = method;
        return this;
    }
    
    public String getAction() {
        return action;
    }
    
    public String getId() {
        return id;
    }
    
    public String getMethod() {
        return method;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    public FormView addContent(Frame2View content) {
        this.contents.add(content);
        return this;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.<String, Object>hashMap()
                       .put("id", id)
                       .put("action", action)
                       .put("method", method)
                       .put("contents", contents)
                       .build();
    }
}
