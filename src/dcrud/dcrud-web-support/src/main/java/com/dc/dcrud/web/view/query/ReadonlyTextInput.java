package com.dc.dcrud.web.view.query;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/14.
 */
public class ReadonlyTextInput extends TextInput {
    public ReadonlyTextInput() {
        super();
        inputView.addAttr("readonly", "readonly");
    }
    
    @Override
    public ReadonlyTextInput addValidator(String validator) {
        return this;
    }
    
    @Override
    public ReadonlyTextInput setId(String id) {
        super.setId(id);
        return this;
    }
}
