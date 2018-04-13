package com.dc.frame2.busi.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.dc.frame2.core.domain.BaseConfigEntity;

/**
 * @author DC
 */
@Table(name = "sys_role")
@Entity
public class RoleEntity extends BaseConfigEntity {
    private static final long serialVersionUID = -1742694681527083908L;
    @Column(length = 63)
    private String code;
    @Column(length = 63)
    private String name;
    @Column(length = 255)
    private String description;
    @ManyToMany(targetEntity = MenuEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    private Set<MenuEntity> alowMenus;
    
    public String getCode() {
        return code;
    }
    
    public RoleEntity setCode(String code) {
        this.code = code;
        return this;
    }
    
    public String getName() {
        return name;
    }
    
    public RoleEntity setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public RoleEntity setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public Set<MenuEntity> getAlowMenus() {
        return alowMenus;
    }
    
    public RoleEntity setAlowMenus(Set<MenuEntity> alowMenus) {
        this.alowMenus = alowMenus;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "RoleEntity{" +
                       "code='" + code + '\'' +
                       ", name='" + name + '\'' +
                       ", description='" + description + '\'' +
                       ", alowMenus=" + alowMenus +
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
