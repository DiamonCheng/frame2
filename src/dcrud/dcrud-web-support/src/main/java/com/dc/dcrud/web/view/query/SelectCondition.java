package com.dc.dcrud.web.view.query;

import com.dc.frame2.view.view.freemarker.form.Option;
import com.dc.frame2.view.view.freemarker.form.SelectView;

import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/17.
 */
public class SelectCondition extends ConditionView {
    protected SelectView selectView;
    
    private String value;
    
    public SelectCondition() {
        selectView = new SelectView();
        selectView.addOption(new Option().setValue("").setText("crud.query.condition.select.option.all"));
        addInput(selectView);
    }
    
    public SelectCondition addOption(Option option) {
        selectView.addOption(option);
        return this;
    }
    
    public List<Option> getOptions() {
        return selectView.getOptions();
    }
    
    public SelectCondition setName(String name) {
        selectView.setName(name);
        return this;
    }
    
    public SelectCondition setValue(String value) {
        this.value = value;
        return this;
    }
    
    public SelectCondition setId(String id) {
        selectView.setId(id);
        return this;
    }
    
    @Override
    public SelectCondition setLabel(String label) {
        super.setLabel(label);
        selectView.setTitle(label);
        return this;
    }
    
    @Override
    public Map<String, Object> getParam() {
        List<Option> options = getOptions();
        if (options != null && value != null) {
            for (Option option : options) {
                if (value.equals(option.getValue())) {
                    option.setSelected(true);
                    break;
                }
            }
        }
        return super.getParam();
    }
}
