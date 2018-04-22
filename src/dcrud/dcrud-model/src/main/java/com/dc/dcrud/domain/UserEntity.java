package com.dc.dcrud.domain;

import com.dc.frame2.core.domain.BaseConfigEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author DC
 */
@Entity
@Table(name = "sys_user", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class UserEntity extends BaseConfigEntity {
    private static final long serialVersionUID = -6918259654521503874L;
    @Column(length = 63)
    private String username;
    @Column(length = 63)
    private String password;
    @Column(length = 63)
    private String nickName;
    @ManyToMany(targetEntity = RoleEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
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
