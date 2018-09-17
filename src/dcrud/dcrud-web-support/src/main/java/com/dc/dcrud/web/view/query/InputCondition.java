package com.dc.dcrud.web.view.query;

import com.dc.frame2.view.view.freemarker.form.InputView;

import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/16.
 */
public class InputCondition extends ConditionView {
    protected InputView inputView;
    
    public InputCondition() {
        inputView = new InputView();
        inputView.addCls("layui-input");
        addInput(inputView);
    }
    
    public InputCondition setName(String name) {
        inputView.setName(name);
        return this;
    }
    
    public InputCondition setValue(String value) {
        inputView.setValue(value);
        return this;
    }
    
    public InputCondition setPlaceHolder(String placeHolder) {
        inputView.setPlaceholder(placeHolder);
        return this;
    }
    
    public InputCondition setId(String id) {
        inputView.setId(id);
        return this;
    }
    
    @Override
    public InputCondition setLabel(String label) {
        super.setLabel(label);
        inputView.setTitle(label);
        return this;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return super.getParam();
    }
}
