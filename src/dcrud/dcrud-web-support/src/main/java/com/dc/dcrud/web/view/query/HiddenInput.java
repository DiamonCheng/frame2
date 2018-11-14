package com.dc.dcrud.web.view.query;

import com.dc.frame2.view.view.freemarker.form.InputView;

import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/14.
 */
public class HiddenInput extends ConditionView {
    private static final String TEMPLATE_NAME = "/common/crud/query/condition.hidden.html.ftl";
    
    protected InputView inputView;
    
    public HiddenInput() {
        inputView = new InputView();
        inputView.setType(InputView.Type.HIDDEN);
        addInput(inputView);
    }
    
    public HiddenInput setName(String name) {
        inputView.setName(name);
        return this;
    }
    
    public HiddenInput setValue(String value) {
        inputView.setValue(value);
        return this;
    }
    
    public HiddenInput setId(String id) {
        inputView.setId(id);
        return this;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return super.getParam();
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
}
