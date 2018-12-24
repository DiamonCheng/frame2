package com.dc.dcrud.web.vo.rbac;

import com.dc.dcrud.web.view.support.viewpojo.edit.EditPanelConfig;
import com.dc.dcrud.web.view.support.viewpojo.inputview.HiddenInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.ReadonlyTextInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.SelectInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TextInput;

import java.util.Arrays;
import java.util.Date;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/16.
 */
@EditPanelConfig(
        editTitle = "crud.userEntity.edit.modify.title"
)
public class UserEntityEditVO {
    private static final long serialVersionUID = -6918259654521503874L;
    @HiddenInput
    private Long id;
    @HiddenInput
    private Integer version;
    @ReadonlyTextInput
    private String username;
    @TextInput(validators = "required")
    private String nickName;
    
    @SelectInput(placeHolder = "crud.edit.field.select.option.toCheck",
            validators = "required",
            optionProvider = "HqlOptionProvider",
            optionProviderKey = "select name as text,id as value from RoleEntity")
    private Long[] roles;
    
    @ReadonlyTextInput(label = "com.dc.dcrud.domain.createDateTime")
    private Date createDateTime;
    @ReadonlyTextInput(label = "com.dc.dcrud.domain.updateDateTime")
    private Date updateDateTime;
    
    public Long getId() {
        return id;
    }
    
    public UserEntityEditVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Date getCreateDateTime() {
        return createDateTime;
    }
    
    public UserEntityEditVO setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }
    
    public Date getUpdateDateTime() {
        return updateDateTime;
    }
    
    public UserEntityEditVO setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }
    
    public String getUsername() {
        return username;
    }
    
    public UserEntityEditVO setUsername(String username) {
        this.username = username;
        return this;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public UserEntityEditVO setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    
    public Long[] getRoles() {
        return roles;
    }
    
    public UserEntityEditVO setRoles(Long[] roles) {
        this.roles = roles;
        return this;
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public UserEntityEditVO setVersion(Integer version) {
        this.version = version;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "UserEntityEditVO{" +
                       "id=" + id +
                       ", version=" + version +
                       ", username='" + username + '\'' +
                       ", nickName='" + nickName + '\'' +
                       ", roles=" + Arrays.toString(roles) +
                       ", createDateTime=" + createDateTime +
                       ", updateDateTime=" + updateDateTime +
                       '}';
    }
}
