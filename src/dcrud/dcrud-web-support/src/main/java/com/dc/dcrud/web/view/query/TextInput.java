package com.dc.dcrud.web.view.query;

import com.dc.frame2.view.view.freemarker.form.InputView;

import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/16.
 */
public class TextInput extends ConditionView {
    protected InputView inputView;
    
    public TextInput() {
        inputView = new InputView();
        inputView.addCls("layui-input");
        addInput(inputView);
    }
    
    public TextInput setName(String name) {
        inputView.setName(name);
        return this;
    }
    
    public TextInput setValue(String value) {
        inputView.setValue(value);
        return this;
    }
    
    public TextInput setPlaceHolder(String placeHolder) {
        inputView.setPlaceholder(placeHolder);
        return this;
    }
    
    public TextInput setId(String id) {
        inputView.setId(id);
        return this;
    }
    
    @Override
    public TextInput setLabel(String label) {
        super.setLabel(label);
        inputView.setTitle(label);
        return this;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return super.getParam();
    }
}
