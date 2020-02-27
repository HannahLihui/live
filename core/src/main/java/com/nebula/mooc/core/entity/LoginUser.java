/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.core.entity;

public class LoginUser {

    private String username = "";
    private String password = "";
    private String code = "";
    private String nickname = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
