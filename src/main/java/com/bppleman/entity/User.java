package com.bppleman.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/8.
 */
public class User implements Serializable{
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String telephone;
    private String type;

    public static final String Normal = "Normal";
    public static final String Admin = "Admin";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String getNormal() {
        return Normal;
    }

    public static String getAdmin() {
        return Admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
