package com.dc.dcrud.domain;

import com.dc.frame2.core.domain.BaseConfigEntity;

import javax.persistence.*;
import java.util.List;

/**
 * @author DC
 */
@Table(name = "sys_operation")
@Entity
public class OperationEntity extends BaseConfigEntity {
    private static final long serialVersionUID = 2283028973673917967L;
    private String code;
    private String requestURI;
    private Boolean isMenu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    private OperationEntity parent;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "none"))
    private List<OperationEntity> children;
    
    public String getCode() {
        
        return code;
    }
    
    public OperationEntity setCode(String code) {
        this.code = code;
        return this;
    }
    
    public String getRequestURI() {
        return requestURI;
    }
    
    public OperationEntity setRequestURI(String requestURI) {
        this.requestURI = requestURI;
        return this;
    }
    
    public Boolean getIsMenu() {
        return isMenu;
    }
    
    public OperationEntity setIsMenu(Boolean isMenu) {
        this.isMenu = isMenu;
        return this;
    }
    
    public OperationEntity getParent() {
        return parent;
    }
    
    public OperationEntity setParent(OperationEntity parent) {
        this.parent = parent;
        return this;
    }
    
    public List<OperationEntity> getChildren() {
        return children;
    }
    
    public OperationEntity setChildren(List<OperationEntity> children) {
        this.children = children;
        return this;
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "OperationEntity{" +
                       "code='" + code + '\'' +
                       ", requestURI='" + requestURI + '\'' +
                       ", isMenu='" + isMenu + '\'' +
                       ", parent=" + parent +
                       ", children=" + children +
                       "} " + super.toString();
    }
}
