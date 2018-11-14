package com.dc.dcrud.web.view.query;

import com.dc.frame2.view.view.freemarker.form.AbstractInput;
import com.dc.frame2.view.view.freemarker.form.InputView;
import com.dc.frame2.view.view.freemarker.form.Option;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/17.
 */
public class CheckboxInput extends ConditionView {
    
    private List<Option> options = new ArrayList<>(3);
    private Collection<String> values;
    private String name = "";
    
    
    @Override
    public CheckboxInput setLabel(String label) {
        super.setLabel(label);
        return this;
    }
    
    public CheckboxInput addOption(Option option) {
        options.add(option);
        return this;
    }
    
    public List<Option> getOptions() {
        return options;
    }
    
    public CheckboxInput setName(String name) {
        this.name = name;
        return this;
    }
    
    public CheckboxInput setValues(Collection<String> values) {
        this.values = values;
        return this;
    }
    
    @Override
    public Map<String, Object> getParam() {
        List<AbstractInput<? extends AbstractInput>> inputs = getInputs();
        inputs.clear();
        for (Option option : options) {
            InputView inputView = new InputView().setType("checkbox").setName(name).setTitle(option.getText()).setValue(option.getValue());
            boolean checked = option.isSelected() || values != null && values.contains(option.getValue());
            if (checked) {
                inputView.addAttr("checked", "checked");
            }
            inputs.add(inputView);
        }
        return super.getParam();
    }
}
