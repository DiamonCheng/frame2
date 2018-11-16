package com.dc.dcrud.web.vo;

import com.dc.dcrud.web.view.support.viewpojo.edit.EditPanelConfig;
import com.dc.dcrud.web.view.support.viewpojo.inputview.ReadonlyTextInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.SelectInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TextInput;

import javax.persistence.Id;
import java.util.Date;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/16.
 */
@EditPanelConfig(
        addTitle = "crud.userEntity.edit.add.title",
        editTitle = "crud.userEntity.edit.modify.title"
)
public class UserEntityVO {
    private static final long serialVersionUID = -6918259654521503874L;
    @Id
    private Long id;
    
    @ReadonlyTextInput
    private String username;
    private String password;
    @TextInput(validators = "required")
    private String nickName;
    
    @SelectInput(placeHolder = "crud.edit.field.select.option.toCheck",
            validators = "required",
            optionProvider = "HqlOptionProvider",
            optionProviderKey = "select name,id from RoleEntity")
    private String[] roles;
    
    @ReadonlyTextInput(label = "com.dc.dcrud.domain.createDateTime")
    private Date createDateTime;
    @ReadonlyTextInput(label = "com.dc.dcrud.domain.updateDateTime")
    private Date updateDateTime;
    
    public Long getId() {
        return id;
    }
    
    public UserEntityVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Date getCreateDateTime() {
        return createDateTime;
    }
    
    public UserEntityVO setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }
    
    public Date getUpdateDateTime() {
        return updateDateTime;
    }
    
    public UserEntityVO setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }
    
    public String getUsername() {
        return username;
    }
    
    public UserEntityVO setUsername(String username) {
        this.username = username;
        return this;
    }
    
    public String getPassword() {
        return password;
    }
    
    public UserEntityVO setPassword(String password) {
        this.password = password;
        return this;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public UserEntityVO setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    
    public String[] getRoles() {
        return roles;
    }
    
    public UserEntityVO setRoles(String[] roles) {
        this.roles = roles;
        return this;
    }
}
