package com.dc.dcrud.web.vo.rbac;

import com.dc.dcrud.web.view.support.viewpojo.edit.EditPanelConfig;
import com.dc.dcrud.web.view.support.viewpojo.inputview.HiddenInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.ReadonlyTextInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TextInput;

import java.util.Arrays;
import java.util.Date;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/21.
 */
@EditPanelConfig(
        editTitle = "crud.roleEntity.edit.modify.title"
)
public class RoleEntityEditVO {
    @HiddenInput
    private Long id;
    @HiddenInput
    private Integer version;
    @TextInput(label = "com.dc.dcrud.domain.RoleEntity.code", validators = "required")
    private String code;
    @TextInput(label = "com.dc.dcrud.domain.RoleEntity.name", validators = "required")
    private String name;
    @TextInput(label = "com.dc.dcrud.domain.RoleEntity.description")
    private String description;
    
    private Long[] resources;
    
    @ReadonlyTextInput(label = "com.dc.dcrud.domain.createDateTime")
    private Date createDateTime;
    
    @ReadonlyTextInput(label = "com.dc.dcrud.domain.updateDateTime")
    private Date updateDateTime;
    
    public String getCode() {
        return code;
    }
    
    public RoleEntityEditVO setCode(String code) {
        this.code = code;
        return this;
    }
    
    public String getName() {
        return name;
    }
    
    public RoleEntityEditVO setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public RoleEntityEditVO setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public Long[] getResources() {
        return resources;
    }
    
    public RoleEntityEditVO setResources(Long[] resources) {
        this.resources = resources;
        return this;
    }
    
    public Date getCreateDateTime() {
        return createDateTime;
    }
    
    public RoleEntityEditVO setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }
    
    public Date getUpdateDateTime() {
        return updateDateTime;
    }
    
    public RoleEntityEditVO setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }
    
    public Long getId() {
        return id;
    }
    
    public RoleEntityEditVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public RoleEntityEditVO setVersion(Integer version) {
        this.version = version;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "RoleEntityEditVO{" +
                       "id=" + id +
                       ", version=" + version +
                       ", code='" + code + '\'' +
                       ", name='" + name + '\'' +
                       ", description='" + description + '\'' +
                       ", resources=" + Arrays.toString(resources) +
                       ", createDateTime=" + createDateTime +
                       ", updateDateTime=" + updateDateTime +
                       '}';
    }
}
