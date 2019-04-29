package com.dc.frame2.core.dto;

import com.dc.frame2.core.dao.condition.ConditionsGroup;

import java.util.List;

/**
 * Base searcher object define. With no condition.
 * <p>Usage: extends it and  add condition fields with annotations. Annotations package : com.dc.frame2.core.dao.conditions
 *
 * @author DC
 * @date 2018/4/14.
 */
public class Searcher<T> implements ConditionsGroup {
    private List<T> resultList;
    
    public List<T> getResultList() {
        return resultList;
    }
    
    public Searcher<T> setResultList(List<T> resultList) {
        this.resultList = resultList;
        return this;
    }
}
