package com.dc.dcrud.web.view.support;

import com.dc.dcrud.web.view.query.QueryPanelView;
import com.dc.dcrud.web.view.support.viewpojo.inputview.InputViewPanelHead;
import com.dc.frame2.core.dao.conditions.ConditionsGroup;
import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.view.Frame2View;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <p> Generate query condition view in page view by page searcher
 * <p> Use a PageSearcher with TextView annotations on fields to configure.</p>
 * @author Diamon.Cheng
 * @date 2018/9/18.
 */
public class QueryPageViewConditionFactory extends InputPanelViewSupport implements QueryViewFactory {
    
    protected String configuredTitle = null;
    /**
     * configure this factory by a PageSearcher with TextView annotations on fields.
     * such as TextInput annotation
     *
     * @param searcher PageSearcher with condition view annotation
     * @return this
     */
    public QueryPageViewConditionFactory configure(PageSearcher searcher) {
        Assert.notNull(searcher, "Searcher cannot be null.");
        configBaseClass = ConditionsGroup.class;
        InputViewPanelHead inputViewPanelHead = searcher.getClass().getAnnotation(InputViewPanelHead.class);
        if (inputViewPanelHead != null) {
            this.configuredTitle = inputViewPanelHead.value();
        }
        fieldViewConfigurations = new ArrayList<>(10);
        resolveConditionGroups(searcher.getClass(), Collections.emptyList(), searcher.getClass());
        return this;
    }
    
    
    @Override
    public Frame2View createPageQueryView(PageSearcher searcher) {
        QueryPanelView queryPanelView = new QueryPanelView();
        //1. 如果没有configure configure 生成 fieldViewConfigurations
        //fieldViewConfigurations.get(0).getViewGenerator().generate()
        //2. 使用ganerators变量解析searcher字段进行视图生成conditionViews 并添加进queryPanelView中。
        if (fieldViewConfigurations == null) {
            configure(searcher);
        }
        if (configuredTitle != null) {
            queryPanelView.setTitle(configuredTitle);
        }
        genInputViews(searcher, queryPanelView::addCondition);
        return queryPanelView;
    }
    
}
