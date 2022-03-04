package cn.edu.xmu.wwf.privilege.controller;

import cn.edu.xmu.wwf.privilege.model.vo.LoginStaffVo;
import cn.edu.xmu.wwf.privilege.service.StaffService;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;
    @PostMapping("/login")
    public ReturnObject login(@RequestBody LoginStaffVo loginStaffVo){
        return staffService.login(loginStaffVo);
    }
}
