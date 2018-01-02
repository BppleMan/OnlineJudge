package com.bppleman.enumration;

public enum UserChangeType {
    USERNAME("username"),
    PASSWORD("password"),
    EMAIL("email"),
    TELEPHONE("telephone"),
    NICKNAME("nickname");

    private String name;

    UserChangeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
