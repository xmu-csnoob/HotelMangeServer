package com.example.bookManageSystem.Service;

import com.example.bookManageSystem.Bean.Admin;
import com.example.bookManageSystem.Mapper.AdminMapper;
import com.example.bookManageSystem.Tools.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminMapper adminMapper;
    @Override
    public List<Admin> selectAll(){ return adminMapper.queryAllAdmins(); }
    @Override
    public int getAdminNum(){ return adminMapper.queryAdminNum(); }
    @Override
    public Admin getAdminByAdminName(String adminName){
        return adminMapper.queryAdminByAdminName(adminName);
    }
    @Override
    public String verifyLogin(Admin admin) {
        if(adminMapper.queryAdminByPassword(admin.getAdminName(), admin.getPassword()).size()!=0)
            return "登录成功";
        else
            return "登录失败";
    }
    @Override
    public String verifyAlterPassword(Admin admin){
        Admin temp=getAdminByAdminName(admin.getAdminName());
        String msg=new Format().checkPasswordFormat(admin.getNewPassword());
        if(!msg.equals("格式正确"))
            return msg;
        if(temp.getPassword().equals(admin.getPassword()))
        {
            adminMapper.updatePasswordByAdminName(admin.getAdminName(),admin.getNewPassword());
            return "修改成功";
        }
        return "修改失败，密码错误";
    }
    @Override
    public String verifyAlterEmail(Admin admin){
        Admin temp=getAdminByAdminName(admin.getAdminName());
        System.out.println("admin.getEmail() = " + admin.getEmail());
        String msg=new Format().checkEmailFormat(admin.getNewEmail());
        if(!msg.equals("格式正确"))
            return msg;
        if(temp.getEmail().equals(admin.getEmail())){
            adminMapper.updateEmailByAdminName(admin.getAdminName(),admin.getNewEmail());
            return "修改成功";
        }
        return "修改失败，邮箱错误";
    }
    public String verifyAlterTel(Admin admin){
        Admin temp=getAdminByAdminName(admin.getAdminName());
        String msg=new Format().checkTelFormat(admin.getNewTel());
        if(!msg.equals("格式正确"))
            return msg;
        if(temp.getTel().equals(admin.getTel())){
            adminMapper.updateTelByAdminName(admin.getAdminName(),admin.getNewTel());
            return "修改成功";
        }
        return "修改失败，电话错误";
    }
}
