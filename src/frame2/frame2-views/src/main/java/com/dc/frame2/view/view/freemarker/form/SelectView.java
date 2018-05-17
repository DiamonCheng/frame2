package com.dc.frame2.view.view.freemarker.form;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.ArrayList;
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
    
    public List<Option> getOptions() {
        return options;
    }
    
    public SelectView addOption(Option option) {
        if (options == null) {
            options = new ArrayList<>(3);
        }
        options.add(option);
        return this;
    }
    
    public SelectView setOptions(List<Option> options) {
        this.options = options;
        return this;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
}
