package com.dc.dcrud.pojo;

import java.io.Serializable;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/4.
 */
public class User implements Serializable {
    public static final String USER_KEY = "USER";
    private Long id;
    private String username;
    private String nickName;
    
    public Long getId() {
        return id;
    }
    
    public User setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getUsername() {
        return username;
    }
    
    public User setUsername(String username) {
        this.username = username;
        return this;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public User setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "User{" +
                       "id=" + id +
                       ", username='" + username + '\'' +
                       ", nickName='" + nickName + '\'' +
                       '}';
    }
}
