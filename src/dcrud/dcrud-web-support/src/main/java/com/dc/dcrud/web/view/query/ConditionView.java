package com.dc.dcrud.web.view.query;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;
import com.dc.frame2.view.view.freemarker.form.AbstractInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConditionView implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/common/crud/query/condition.html.ftl";
    private List<AbstractInput<? extends AbstractInput>> inputs = new ArrayList<>();
    private String label = "";
    
    public List<AbstractInput<? extends AbstractInput>> getInputs() {
        return inputs;
    }
    
    public ConditionView addInput(AbstractInput<? extends AbstractInput> input) {
        inputs.add(input);
        return this;
    }
    
    public ConditionView setLabel(String label) {
        this.label = label;
        return this;
    }
    
    public String getLabel() {
        return label;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap()
                       .put("inputs", inputs)
                       .put("label", label)
                       .build();
    }
}