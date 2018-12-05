package com.dc.dcrud.domain;

import com.dc.dcrud.extractor.UserEntityRoleDataExtractor;
import com.dc.dcrud.web.view.support.viewpojo.edit.EditPanelConfig;
import com.dc.dcrud.web.view.support.viewpojo.inputview.SelectInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TextInput;
import com.dc.frame2.core.domain.BaseConfigEntity;
import com.dc.frame2.data.Extractor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author DC
 */

@Entity
@Table(name = "sys_user", uniqueConstraints = @UniqueConstraint(name = "unique_username", columnNames = {"username"}))
@EditPanelConfig(
        addTitle = "crud.userEntity.edit.add.title"
)
public class UserEntity extends BaseConfigEntity {
    private static final long serialVersionUID = -6918259654521503874L;
    
    @Column(length = 63, nullable = false)
    @TextInput(validators = "required")
    private String username;
    @Column(length = 63, nullable = false)
    private String password;
    @Column(length = 63, nullable = false)
    @TextInput(validators = "required")
    private String nickName;
    
    @SelectInput(placeHolder = "crud.edit.field.select.option.toCheck",
            name = "roles",
            optionProvider = "HqlOptionProvider",
            optionProviderKey = "select name,id from RoleEntity")
    @ManyToMany(targetEntity = RoleEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    @Extractor(UserEntityRoleDataExtractor.class)
    private Set<RoleEntity> roles;
    
    public String getUsername() {
        return username;
    }
    
    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }
    
    public String getPassword() {
        return password;
    }
    
    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public UserEntity setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    
    public Set<RoleEntity> getRoles() {
        return roles;
    }
    
    public UserEntity setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "UserEntity{" +
                       "username='" + username + '\'' +
                       ", password='" + password + '\'' +
                       ", nickName='" + nickName + '\'' +
                       ", roles=" + roles +
                       "} " + super.toString();
    }
}


