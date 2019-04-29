package com.dc.dcrud.model.domain;

import com.dc.dcrud.extractor.RoleEntityDataExtractor;
import com.dc.dcrud.web.view.support.viewpojo.edit.EditPanelConfig;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TextInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TreeSelectInput;
import com.dc.frame2.core.domain.BaseConfigEntity;
import com.dc.frame2.data.Extractor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Set;

/**
 * @author DC
 */
@Table(name = "sys_role", uniqueConstraints = @UniqueConstraint(name = "sys_role_code_unique", columnNames = "code"))
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EditPanelConfig(
        addTitle = "crud.roleEntity.edit.add.title"
)
public class RoleEntity extends BaseConfigEntity {
    private static final long serialVersionUID = -1742694681527083908L;
    @Column(length = 63, nullable = false)
    @TextInput(validators = "required")
    private String code;
    @Column(length = 63, nullable = false)
    @TextInput(validators = "required")
    private String name;
    @Column
    @TextInput()
    private String description;
    @ManyToMany(targetEntity = ResourceEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    @Extractor(RoleEntityDataExtractor.class)
    @TreeSelectInput(optionProvider = "resourceService")
    private Set<ResourceEntity> resources;
    
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
    
    public Set<ResourceEntity> getResources() {
        return resources;
    }
    
    public RoleEntity setResources(Set<ResourceEntity> resources) {
        this.resources = resources;
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
                       ", resources=" + resources +
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
