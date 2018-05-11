package com.dc.dcrud.web.view;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/6.
 */
public class DefaultDataTableView implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/common/crud/query/table.html.ftl";
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap().build();
    }
}
