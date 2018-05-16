package com.dc.dcrud.web.view.query;

import com.dc.frame2.view.view.freemarker.form.InputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/16.
 */
public class InputCondition extends ConditionView {
    private InputView inputView;
    /**
     * 注：规则在validator-list.js中
     */
    private List<String> validators;
    
    public InputCondition() {
        inputView = new InputView();
        inputView.addCls("layui-input");
        validators = new ArrayList<>();
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
    
    public InputCondition addValidator(String validator) {
        if (!validators.contains(validator)) {
            validators.add(validator);
        }
        return this;
    }
    
    /**
     * 注：规则在validator-list.js中
     */
    public List<String> getValidators() {
        return validators;
    }
    
    @Override
    public InputCondition setLabel(String label) {
        super.setLabel(label);
        inputView.setTitle(label);
        return this;
    }
    
    @Override
    public Map<String, Object> getParam() {
        inputView.getAttrs().put("validator", validators.stream().collect(Collectors.joining(",")));
        return super.getParam();
    }
}
