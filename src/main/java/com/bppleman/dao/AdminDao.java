package com.bppleman.dao;

import com.bppleman.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {
    List<Admin> getAllAdmins();
    Admin getAdminByAdminName(String adminName);
    Integer insertAdmin(Admin admin);
    Integer updateAdmin(Admin admin);
}
