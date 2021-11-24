package com.example.bookManageSystem.Mapper;

import com.example.bookManageSystem.Bean.Admin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminMapper {
    //查询所有管理员信息
    List<Admin> queryAllAdmins();
    //查询管理员个数
    int queryAdminNum();
    //根据用户名和密码查询数据库中管理员记录
    List<Admin> queryAdminByPassword(String adminName,String password);
    //根据用户名查询管理员记录
    Admin queryAdminByAdminName(String adminName);
    //根据管理员名修改相应管理员记录密码字段
    void updatePasswordByAdminName(String adminName,String newPassword);
    //根据管理员名修改相应管理员记录邮箱字段
    void updateEmailByAdminName(String adminName,String newEmail);
    //根据管理员名修改相应管理员记录电话字段
    void updateTelByAdminName(String adminName,String newTel);
    //Only For Tool Use:获取当前日期
    String getCurrentDate();
}