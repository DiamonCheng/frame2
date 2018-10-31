package com.dc.dcrud.web.view.query;

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
public class DatePickerInput extends TextInput {
    private Map<String, String> options = new HashMap<>();
    
    public DatePickerInput() {
        super();
        inputView.addCls("date-picker");
    }
    
    public Map<String, String> getOptions() {
        return options;
    }
    
    public DatePickerInput addOption(String key, String val) {
        options.put(key, val);
        return this;
    }
    
    @Override
    public Map<String, Object> getParam() {
        try {
            inputView.addAttr("data-picker-option", HtmlUtils.htmlEscape(new ObjectMapper().writeValueAsString(options)));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
        return super.getParam();
    }
}
