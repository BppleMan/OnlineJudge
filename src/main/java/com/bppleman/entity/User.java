package com.bppleman.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/8.
 */
public class User implements Serializable{
    private int id;
    private String username;
    private String password;
    private String email;
    private String type;

    public static final String Normal = "Normal";
    public static final String Admin = "Admin";


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + "\'" +
                ", password='" + password + "\'" +
                ", email='" + email + "\'" +
                ", type='" + type + "\'" +
                "}\n";
    }
}
