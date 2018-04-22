package com.dc.dcrud.domain;

import com.dc.frame2.core.domain.BaseConfigEntity;

import javax.persistence.*;
import java.util.Set;

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
    @ManyToMany(targetEntity = OperationEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    private Set<OperationEntity> permissions;
    
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
    
    public Set<OperationEntity> getPermissions() {
        return permissions;
    }
    
    public RoleEntity setPermissions(Set<OperationEntity> permissions) {
        this.permissions = permissions;
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
                       ", permissions=" + permissions +
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
