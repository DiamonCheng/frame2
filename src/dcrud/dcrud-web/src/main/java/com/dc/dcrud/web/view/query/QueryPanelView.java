package com.dc.dcrud.web.view.query;

import com.dc.frame2.util.MapBuilder;
import com.dc.frame2.view.view.freemarker.FreemarkerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/5/6.
 */
public class QueryPanelView implements FreemarkerView {
    private static final String TEMPLATE_NAME = "/common/crud/query/conditions.html.ftl";
    
    private List<ConditionView> conditions = new ArrayList<>(3);
    private String title = "";
    
    public List<ConditionView> getConditions() {
        return conditions;
    }
    
    public QueryPanelView addCondition(ConditionView condition) {
        conditions.add(condition);
        return this;
    }
    
    public QueryPanelView setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        return MapBuilder.dataMap()
                       .put("title", title)
                       .put("conditions", conditions)
                       .build();
    }
}
