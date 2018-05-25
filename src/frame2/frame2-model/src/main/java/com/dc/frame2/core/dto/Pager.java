package com.dc.frame2.core.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Custom a pager with no default constructor
 *
 * @author DC
 * @date 2018/4/14.
 */
public class Pager implements Pageable, Serializable, Cloneable {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE = 0;
    private static final String DESC = "DESC";
    private static final String ASC = "ASC";
    private static final String DEFAULT_DIRECTION = DESC;
    private static final String ORDER_SPLIT_CHAR = " ";
    
    private int pageNo = DEFAULT_PAGE;
    private int pageSize = DEFAULT_PAGE_SIZE;
    
    private List<String> orderBy = new ArrayList<>();
    
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
        if (orderBy == null) {
            return null;
        }
        List<Sort.Order> orders = new ArrayList<>(orderBy.size());
        for (String orderString : orderBy) {
            String[] orderSplit = orderString.split(ORDER_SPLIT_CHAR);
            if (StringUtils.isEmpty(orderSplit[0])) {
                continue;
            }
            String propertyName = orderSplit[0];
            String direction = DEFAULT_DIRECTION;
            if (orderSplit.length > 1) {
                direction = orderSplit[1];
            }
            if (ASC.equalsIgnoreCase(direction)) {
                orders.add(new Sort.Order(Sort.Direction.ASC, propertyName));
            } else if (DESC.equalsIgnoreCase(direction)) {
                orders.add(new Sort.Order(Sort.Direction.DESC, propertyName));
            }
        }
        return orders.isEmpty() ? null : new Sort(orders);
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
    
    public Pager setOrderBy(List<String> orderBy) {
        this.orderBy = orderBy;
        return this;
    }
    
    public List<String> getOrderBy() {
        return orderBy;
    }
    
    @Override
    public Pager clone() throws CloneNotSupportedException {
        return (Pager) super.clone();
    }
}
