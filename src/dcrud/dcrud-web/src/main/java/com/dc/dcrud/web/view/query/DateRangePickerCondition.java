package com.dc.dcrud.web.view.query;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.form.InputView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/17.
 */
public class DateRangePickerCondition extends InputCondition {
    private Map<String, String> options = new HashMap<>();
    protected InputView startInput;
    protected InputView endInput;
    
    public DateRangePickerCondition() {
        super();
        inputView.addCls("date-range-picker");
        startInput = new InputView().setName("start").addCls("date-range-picker-value").setType("hidden");
        endInput = new InputView().setName("end").addCls("date-range-picker-value").setType("hidden");
        addInput(startInput);
        addInput(endInput);
    }
    
    public String getStartName() {
        return startInput.getName();
    }
    
    public DateRangePickerCondition setStartName(String startName) {
        startInput.setName(startName);
        return this;
    }
    
    public String getEndName() {
        return endInput.getName();
    }
    
    public DateRangePickerCondition setEndName(String endName) {
        endInput.setName(endName);
        return this;
    }
    
    public String getStartValue() {
        return startInput.getValue();
    }
    
    public DateRangePickerCondition setStartValue(String startValue) {
        this.startInput.setValue(startValue);
        return this;
    }
    
    public String getEndValue() {
        return endInput.getValue();
    }
    
    public DateRangePickerCondition setEndValue(String endValue) {
        this.endInput.setValue(endValue);
        return this;
    }
    
    public Map<String, String> getOptions() {
        return options;
    }
    
    public DateRangePickerCondition addOption(String key, String val) {
        options.put(key, val);
        return this;
    }
    
    @Override
    public Map<String, Object> getParam() {
        try {
            inputView.addAttr("data-picker-option", HtmlUtils.htmlEscape(new ObjectMapper().writeValueAsString(options)));
            inputView.addAttr("data-range-option", HtmlUtils.htmlEscape(
                    new ObjectMapper().writeValueAsString(
                            MapBuilder.<String, String>hashMap()
                                    .put("start", startInput.getName())
                                    .put("end", endInput.getName())
                                    .build()
                    )
            ));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
        return super.getParam();
    }
}
