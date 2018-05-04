package com.dc.dcrud.pojo;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/5/4.
 */
public class User {
    public static final String USER_KEY = "USER";
    private String username;
    private String nickName;
    
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
}
