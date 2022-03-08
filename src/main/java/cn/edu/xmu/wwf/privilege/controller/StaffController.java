package cn.edu.xmu.wwf.privilege.controller;

import cn.edu.xmu.wwf.privilege.model.vo.LoginStaffVo;
import cn.edu.xmu.wwf.privilege.service.StaffService;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

@RestController
@CrossOrigin
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;
    @Autowired
    HttpServletResponse response;
    @PostMapping("/login")
    public ReturnObject login(@RequestBody LoginStaffVo loginStaffVo){
        return staffService.login(loginStaffVo);
    }
    @GetMapping("")
    public ReturnObject getStaff(@RequestParam int pageNum,@RequestParam int pageSize){
        return staffService.getAllStaffInfo(pageNum,pageSize);
    }
    @GetMapping("/depart/{departId}")
    public ReturnObject getStaffInDepart(@RequestParam int pageNum,@RequestParam int pageSize,@PathVariable int departId){
        return staffService.getStaffInfoByDepartId(pageNum,pageSize,departId);
    }
}
