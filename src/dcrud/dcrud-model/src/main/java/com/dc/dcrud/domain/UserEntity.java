package com.dc.dcrud.domain;

import com.dc.dcrud.extractor.UserEntityRoleDataExtractor;
import com.dc.dcrud.web.view.support.viewpojo.edit.EditPanelConfig;
import com.dc.dcrud.web.view.support.viewpojo.inputview.ReadonlyTextInput;
import com.dc.dcrud.web.view.support.viewpojo.inputview.TextInput;
import com.dc.frame2.core.domain.BaseConfigEntity;
import com.dc.frame2.data.Extractor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author DC
 */

@EditPanelConfig(
        addTitle = "crud.userEntity.edit.add.title",
        editTitle = "crud.userEntity.edit.modify.title"
)
@Entity
@Table(name = "sys_user", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class UserEntity extends BaseConfigEntity {
    private static final long serialVersionUID = -6918259654521503874L;
    
    @ReadonlyTextInput
    @Column(length = 63)
    private String username;
    @Column(length = 63)
    private String password;
    @Column(length = 63)
    @TextInput(validators = "required")
    private String nickName;
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


