package com.dc.dcrud.web.view.edit;

import com.dc.dcrud.web.view.query.QueryPanelView;

import java.util.Map;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/12.
 */
public class EditPanelView extends QueryPanelView {
    private static final String TEMPLATE_NAME = "/common/crud/edit/edit.panel.html.ftl";
    private String backHref = "./";
    
    public EditPanelView setBackHref(String backHref) {
        this.backHref = backHref;
        return this;
    }
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
    
    @Override
    public Map<String, Object> getParam() {
        Map<String, Object> map = super.getParam();
        map.put("backHref", backHref);
        return map;
    }
}
