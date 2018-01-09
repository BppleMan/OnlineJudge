package com.bppleman.service;

import com.bppleman.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins();
    boolean loginAdmin(Admin toLogin);
    boolean insertAdmin(Admin admin);
    boolean updateAdmin(Admin admin);
}
