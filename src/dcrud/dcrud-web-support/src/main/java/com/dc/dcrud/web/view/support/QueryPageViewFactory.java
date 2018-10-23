package com.dc.dcrud.web.view.support;

import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.view.freemarker.form.FormView;
import com.dc.frame2.view.view.freemarker.page.PageView;

/**
 * <p>Generate query page view with query condition view, button option view and query data view.
 *
 * @author Diamon.Cheng
 * @date 2018/10/23.
 */
public class QueryPageViewFactory implements QueryViewFactory {
    
    private QueryConditionViewFactory queryConditionViewFactory = new QueryConditionViewFactory();
    
    @Override
    public Frame2View createPageQueryView(PageSearcher searcher) {
        PageView pageView = new PageView();
        FormView formView = new FormView();
        pageView.addComponent(formView);
        formView.setId("pageFrom");
        Frame2View conditionView = queryConditionViewFactory.createPageQueryView(searcher);
        formView.addContent(conditionView);
        return pageView;
    }
    
    public QueryPageViewFactory setQueryConditionViewFactory(QueryConditionViewFactory queryConditionViewFactory) {
        this.queryConditionViewFactory = queryConditionViewFactory;
        return this;
    }
}
