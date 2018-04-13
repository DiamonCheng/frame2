package com.dc.frame2.busi.domain;

import com.dc.frame2.core.domain.BaseConfigEntity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author DC
 */
@Entity
@Table(name = "sys_user", uniqueConstraints = @UniqueConstraint(columnNames = {"userName"}))
public class UserEntity extends BaseConfigEntity {
    private static final long serialVersionUID = -6918259654521503874L;
    @Column(length = 63)
    private String userName;
    @Column(length = 63)
    private String userPassword;
    @Column(length = 63)
    private String nickName;
    @ManyToMany(targetEntity = RoleEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    private Set<RoleEntity> roles;
    
    public String getUserName() {
        return userName;
    }
    
    public UserEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    
    public String getUserPassword() {
        return userPassword;
    }
    
    public UserEntity setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
                       "userName='" + userName + '\'' +
                       ", userPassword='" + userPassword + '\'' +
                       ", nickName='" + nickName + '\'' +
                       ", roles=" + roles +
                       "} " + super.toString();
    }
}
