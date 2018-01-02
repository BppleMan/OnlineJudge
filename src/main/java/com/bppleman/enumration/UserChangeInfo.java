package com.bppleman.enumration;

public enum UserChangeInfo {
    SUCCESS("success"), ERROR("danger"), PASSWORD_ERROR("原旧密码错误");
    private String value;

    UserChangeInfo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
