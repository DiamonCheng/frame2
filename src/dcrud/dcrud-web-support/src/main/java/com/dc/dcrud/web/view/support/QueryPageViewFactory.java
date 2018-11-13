package com.dc.dcrud.web.view.support;

import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.view.Frame2View;
import com.dc.frame2.view.view.freemarker.form.FormView;
import com.dc.frame2.view.view.freemarker.page.JsResource;
import com.dc.frame2.view.view.freemarker.page.PageView;
import com.dc.frame2.view.view.freemarker.page.Resource;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Generate query page view with query condition view, button option view and query data view.
 *
 * @author Diamon.Cheng
 * @date 2018/10/23.
 */
public class QueryPageViewFactory implements QueryViewFactory {
    
    private QueryPageViewConditionFactory queryPageViewConditionFactory = new QueryPageViewConditionFactory();
    
    private QueryPageViewOptionButtonFactory queryPageViewOptionButtonFactory = new QueryPageViewOptionButtonFactory();
    
    private QueryPageViewDataTableFactory queryPageViewDataTableFactory = new QueryPageViewDataTableFactory();
    
    private List<Resource> headResources = new LinkedList<>();
    
    private List<JsResource> buttonJsResources = new LinkedList<>(Collections.singleton(new JsResource().setPath("/resources/js/common/query.table.support.js")));
    
    @Override
    public Frame2View createPageQueryView(PageSearcher searcher) {
        PageView pageView = new PageView();
        headResources.forEach(pageView::addHeadResource);
        buttonJsResources.forEach(pageView::addBottomJsResource);
        FormView formView = new FormView();
        pageView.addComponent(formView);
        formView.setId("pageForm");
        Frame2View conditionView = queryPageViewConditionFactory.createPageQueryView(searcher);
        formView.addContent(conditionView);
        Frame2View optionButtonsView = queryPageViewOptionButtonFactory.createPageQueryView(searcher);
        formView.addContent(optionButtonsView);
        Frame2View dataTableView = queryPageViewDataTableFactory.createPageQueryView(searcher);
        formView.addContent(dataTableView);
        return pageView;
    }
    
    /**
     * TODO
     *
     * @param configurePojo a PageSearcher instance with Page configure annotations
     */
    public QueryPageViewFactory configure(PageSearcher configurePojo) {
        queryPageViewConditionFactory.configure(configurePojo);
        queryPageViewOptionButtonFactory.configure(configurePojo);
        queryPageViewDataTableFactory.configure(configurePojo);
        return this;
    }
    
    public QueryPageViewFactory setQueryPageViewConditionFactory(QueryPageViewConditionFactory queryPageViewConditionFactory) {
        this.queryPageViewConditionFactory = queryPageViewConditionFactory;
        return this;
    }
    
    public QueryPageViewConditionFactory getQueryPageViewConditionFactory() {
        return queryPageViewConditionFactory;
    }
    
    public QueryPageViewOptionButtonFactory getQueryPageViewOptionButtonFactory() {
        return queryPageViewOptionButtonFactory;
    }
    
    public QueryPageViewFactory setQueryPageViewOptionButtonFactory(QueryPageViewOptionButtonFactory queryPageViewOptionButtonFactory) {
        this.queryPageViewOptionButtonFactory = queryPageViewOptionButtonFactory;
        return this;
    }
    
    public QueryPageViewFactory setQueryPageViewDataTableFactory(QueryPageViewDataTableFactory queryPageViewDataTableFactory) {
        this.queryPageViewDataTableFactory = queryPageViewDataTableFactory;
        return this;
    }
    
    public QueryPageViewDataTableFactory getQueryPageViewDataTableFactory() {
        return queryPageViewDataTableFactory;
    }
    
    public List<JsResource> getButtonJsResources() {
        return buttonJsResources;
    }
    
    public List<Resource> getHeadResources() {
        return headResources;
    }
    
    public QueryPageViewFactory addButtonJsResources(JsResource jsResource) {
        buttonJsResources.add(jsResource);
        return this;
    }
    
    public QueryPageViewFactory addHeadResources(Resource resource) {
        headResources.add(resource);
        return this;
    }
    
}
