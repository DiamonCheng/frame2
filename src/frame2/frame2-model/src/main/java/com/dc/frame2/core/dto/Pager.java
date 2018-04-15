package com.dc.frame2.core.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * <p>Custom a pager with no default constructor
 *
 * @author DC
 * @date 2018/4/14.
 */
public class Pager implements Pageable, Serializable, Cloneable {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE = 0;
    
    private int pageNo = DEFAULT_PAGE;
    private int pageSize = DEFAULT_PAGE_SIZE;
    
    private Sort sort;
    
    @Override
    public int getPageNumber() {
        return pageNo;
    }
    
    @Override
    public int getPageSize() {
        return pageSize;
    }
    
    @Override
    public int getOffset() {
        return pageNo * pageSize;
    }
    
    @Override
    public Sort getSort() {
        return sort;
    }
    
    @Override
    public Pager next() {
        try {
            return this.clone().setPageNo(pageNo + 1);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    
    @Override
    public Pager previousOrFirst() {
        try {
            return this.clone().setPageNo(pageNo > 0 ? 0 : pageNo - 1);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Override
    public Pager first() {
        try {
            return this.clone().setPageNo(0);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Override
    public boolean hasPrevious() {
        return pageNo == 0;
    }
    
    public int getPageNo() {
        return pageNo;
    }
    
    public Pager setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }
    
    public Pager setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
    
    public Pager setSort(Sort sort) {
        this.sort = sort;
        return this;
    }
    
    @Override
    public Pager clone() throws CloneNotSupportedException {
        return (Pager) super.clone();
    }
}
