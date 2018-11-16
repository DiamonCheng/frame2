package com.dc.dcrud.web.view.query;

import com.dc.frame2.view.view.freemarker.form.Option;
import com.dc.frame2.view.view.freemarker.form.SelectView;
import org.springframework.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/17.
 */
public class SelectInput extends ConditionView {
    protected SelectView selectView;
    protected LinkedHashSet<String> validators;
    private String value;
    public SelectInput() {
        selectView = new SelectView();
        addInput(selectView);
    }
    public SelectInput addOption(Option option) {
        selectView.addOption(option);
        return this;
    }
    
    public List<Option> getOptions() {
        return selectView.getOptions();
    }
    
    public SelectInput multipleSelect(boolean flag) {
        if (flag) {
            selectView.addAttr("xm-select", "").addAttr("xm-select-search", "").addAttr("multiple", "multiple");
        } else {
            selectView.removeAttr("xm-select").removeAttr("xm-select-search").removeAttr("multiple");
        }
        return this;
    }
    
    public SelectInput setName(String name) {
        selectView.setName(name);
        return this;
    }
    
    public SelectInput setValue(String value) {
        this.value = value;
        return this;
    }
    
    public SelectInput setId(String id) {
        selectView.setId(id);
        return this;
    }
    
    @Override
    public SelectInput setLabel(String label) {
        super.setLabel(label);
        selectView.setTitle(label);
        return this;
    }
    
    public SelectInput addValidator(String validator) {
        if (validators == null) {
            validators = new LinkedHashSet<>();
        }
        validators.add(validator);
        selectView.addAttr("validator", validators.stream().reduce("", (a, b) -> StringUtils.isEmpty(a) ? b : (a + "|" + b)));
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
