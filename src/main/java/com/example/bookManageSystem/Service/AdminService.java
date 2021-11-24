package com.example.bookManageSystem.Service;

import com.example.bookManageSystem.Bean.Admin;

import java.util.List;

public interface AdminService {
    //提供信息
    List<Admin> selectAll();
    int getAdminNum();
    Admin getAdminByAdminName(String adminName);
    //提供服务
    String verifyLogin(Admin admin);
    String verifyAlterPassword(Admin admin);
    String verifyAlterEmail(Admin admin);
    String verifyAlterTel(Admin admin);
}
