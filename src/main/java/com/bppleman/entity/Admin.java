package com.bppleman.entity;

public class Admin {
    private String adminName;
    private String password;
    private String teacherCard;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeacherCard() {
        return teacherCard;
    }

    public void setTeacherCard(String teacherCard) {
        this.teacherCard = teacherCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (adminName != null ? !adminName.equals(admin.adminName) : admin.adminName != null) return false;
        if (password != null ? !password.equals(admin.password) : admin.password != null) return false;
        return teacherCard != null ? teacherCard.equals(admin.teacherCard) : admin.teacherCard == null;
    }

    @Override
    public int hashCode() {
        int result = adminName != null ? adminName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (teacherCard != null ? teacherCard.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", teacherCard='" + teacherCard + '\'' +
                '}';
    }
}
