package com.dc.frame2.core.dto;

import com.dc.frame2.core.dao.conditions.ConditionsGroup;

import java.util.List;

/**
 * Base page searcher object define. With no condition.
 * <p>Usage: extends it and  add condition fields with annotations. Annotations package : com.dc.frame2.core.dao.conditions
 * <p>Note: The page no is start with 0.
 *
 * @author DC
 * @date 2018/4/14.
 */
public abstract class PageSearcher<T> extends Pager implements ConditionsGroup {
    private List<T> resultList;
    private Long totalCount;
    
    public List<T> getResultList() {
        return resultList;
    }
    
    public PageSearcher<T> setResultList(List<T> resultList) {
        this.resultList = resultList;
        return this;
    }
    
    public Long getTotalCount() {
        return totalCount;
    }
    
    public PageSearcher<T> setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
        return this;
    }
    
    public abstract Class<T> getViewObjectClass();
}
