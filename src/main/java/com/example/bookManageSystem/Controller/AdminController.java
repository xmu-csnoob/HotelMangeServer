package com.example.bookManageSystem.Controller;

import com.example.bookManageSystem.Bean.Admin;
import com.example.bookManageSystem.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    //提供信息
    @RequestMapping("/admins")
    public Object admin(){
        return adminService.selectAll();
    }
    @RequestMapping("/adminNum")
    public int adminNum(){
        return adminService.getAdminNum();
    }
    @RequestMapping("/adminInfo")
    public Admin getInfoByName(@RequestParam("adminName")String adminName){
        return adminService.getAdminByAdminName(adminName);
    }
    //提供事件响应
    @RequestMapping(value="/login",produces = "application/json;charset=UTF-8")
    public String login(@RequestBody Admin admin){
        return adminService.verifyLogin(admin);
    }
    @RequestMapping("/alterPassword")
    public String alterPwd(@RequestBody Admin admin){
        return adminService.verifyAlterPassword(admin);
    }
    @RequestMapping("/alterEmail")
    public String alterEmail(@RequestBody Admin admin){
        return adminService.verifyAlterEmail(admin);
    }
    @RequestMapping("/alterTel")
    public String alterTel(@RequestBody Admin admin){
        return adminService.verifyAlterTel(admin);
    }
}
