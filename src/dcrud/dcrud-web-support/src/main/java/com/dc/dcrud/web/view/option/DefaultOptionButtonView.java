package com.dc.dcrud.web.view.option;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/11.
 */
public class DefaultOptionButtonView implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/common/crud/option/buttons.html.ftl";
    
    private List<Frame2View> buttons = new ArrayList<>();
    
    public List<Frame2View> getButtons() {
        return buttons;
    }
    
    public DefaultOptionButtonView addButton(OptionButton optionButton) {
        buttons.add(optionButton);
        return this;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap().put("buttons", buttons).build();
    }
}
