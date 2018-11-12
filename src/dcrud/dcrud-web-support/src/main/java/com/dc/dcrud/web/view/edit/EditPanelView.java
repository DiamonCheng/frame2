package com.dc.dcrud.web.view.edit;

import com.dc.dcrud.web.view.query.QueryPanelView;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/12.
 */
public class EditPanelView extends QueryPanelView {
    private static final String TEMPLATE_NAME = "/common/crud/edit/edit.panel.html.ftl";
    
    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
}
