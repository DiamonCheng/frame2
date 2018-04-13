package com.dc.frame2.busi.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dc.frame2.core.domain.BaseConfigEntity;

/**
 * @author DC
 */
@Table(name = "sys_menu")
@Entity
public class MenuEntity extends BaseConfigEntity {
    private static final long serialVersionUID = 2283028973673917967L;
    private String displayName;
    private String requestURI;
    private boolean hidden;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    private MenuEntity parent;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "none"))
    private List<MenuEntity> children;
    
    public String getDisplayName() {
        return displayName;
    }
    
    public MenuEntity setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }
    
    public String getRequestURI() {
        return requestURI;
    }
    
    public MenuEntity setRequestURI(String requestURI) {
        this.requestURI = requestURI;
        return this;
    }
    
    public boolean isHidden() {
        return hidden;
    }
    
    public MenuEntity setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }
    
    public MenuEntity getParent() {
        return parent;
    }
    
    public MenuEntity setParent(MenuEntity parent) {
        this.parent = parent;
        return this;
    }
    
    public List<MenuEntity> getChildren() {
        return children;
    }
    
    public MenuEntity setChildren(List<MenuEntity> children) {
        this.children = children;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "MenuEntity{" +
                       "displayName='" + displayName + '\'' +
                       ", requestURI='" + requestURI + '\'' +
                       ", hidden=" + hidden +
                       ", parent=" + parent +
                       ", children=" + children +
                       "} " + super.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
