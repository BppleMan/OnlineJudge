package com.bppleman.service.impl;

import com.bppleman.dao.AdminDao;
import com.bppleman.entity.Admin;
import com.bppleman.service.AdminService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Override
    public List<Admin> getAllAdmins() {
        return adminDao.getAllAdmins();
    }

    @Override
    public boolean loginAdmin(Admin toLogin) {
        boolean result = false;
        Admin admin = adminDao.getAdminByAdminName(toLogin.getAdminName());
        if (admin != null && admin.getPassword().equals(toLogin.getPassword())) {
            toLogin.cloneFrom(admin);
            result = true;
        }
        return result;
    }

    @Override
    public boolean insertAdmin(Admin admin) {
        boolean result = true;
        if (adminDao.insertAdmin(admin) != 1)
            result = false;
        return result;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        boolean result = true;
        if (adminDao.updateAdmin(admin) != 1)
            result = false;
        return result;
    }
}
