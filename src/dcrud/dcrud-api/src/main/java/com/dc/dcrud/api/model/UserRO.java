package com.dc.dcrud.api.model;

import java.util.Date;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2019/4/16.
 */
public class UserRO {
    private Long id;
    private Integer version;
    private Date createDateTime;
    private Date updateDateTime;
    private String username;
    private String nickName;
    
    public Long getId() {
        return id;
    }
    
    public UserRO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public UserRO setVersion(Integer version) {
        this.version = version;
        return this;
    }
    
    public Date getCreateDateTime() {
        return createDateTime;
    }
    
    public UserRO setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }
    
    public Date getUpdateDateTime() {
        return updateDateTime;
    }
    
    public UserRO setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }
    
    public String getUsername() {
        return username;
    }
    
    public UserRO setUsername(String username) {
        this.username = username;
        return this;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public UserRO setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
}
