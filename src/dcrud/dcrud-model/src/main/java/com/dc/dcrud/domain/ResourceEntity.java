package com.dc.dcrud.domain;

import com.dc.frame2.core.domain.BaseConfigEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

/**
 * @author DC
 */
@Table(name = "sys_resource", uniqueConstraints = @UniqueConstraint(name = "sys_resource_code_unique", columnNames = "code"))
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ResourceEntity extends BaseConfigEntity {
    public static class Type {
        public static final String MENU = "menu";
    }
    private static final long serialVersionUID = 2283028973673917967L;
    @Column(nullable = false)
    private String code;
    private String requestURI;
    private String type;
    private Integer sortOrder;
    private String iconClass;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    private ResourceEntity parent;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "none"))
    @OrderBy("sortOrder")
    private List<ResourceEntity> children;
    
    public String getCode() {
        
        return code;
    }
    
    public ResourceEntity setCode(String code) {
        this.code = code;
        return this;
    }
    
    public String getRequestURI() {
        return requestURI;
    }
    
    public ResourceEntity setRequestURI(String requestURI) {
        this.requestURI = requestURI;
        return this;
    }
    
    public String getType() {
        
        return type;
    }
    
    public ResourceEntity setType(String type) {
        this.type = type;
        return this;
    }
    
    public ResourceEntity getParent() {
        return parent;
    }
    
    public ResourceEntity setParent(ResourceEntity parent) {
        this.parent = parent;
        return this;
    }
    
    public List<ResourceEntity> getChildren() {
        return children;
    }
    
    public ResourceEntity setChildren(List<ResourceEntity> children) {
        this.children = children;
        return this;
    }
    
    public Integer getSortOrder() {
        return sortOrder;
    }
    
    public ResourceEntity setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }
    
    public String getIconClass() {
        return iconClass;
    }
    
    public ResourceEntity setIconClass(String iconClass) {
        this.iconClass = iconClass;
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
        return "ResourceEntity{" +
                       "code='" + code + '\'' +
                       ", requestURI='" + requestURI + '\'' +
                       ", type='" + type + '\'' +
                       ", sortOrder=" + sortOrder +
                       ", iconClass=" + iconClass +
                       ", children=" + children +
                       ", id=" + id +
                       ", version=" + version +
                       ", createDateTime=" + createDateTime +
                       ", updateDateTime=" + updateDateTime +
                       '}';
    }
}
