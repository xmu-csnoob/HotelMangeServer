package cn.edu.xmu.wwf.privilege.controller;

import cn.edu.xmu.wwf.privilege.model.vo.LoginStaffVo;
import cn.edu.xmu.wwf.privilege.service.StaffService;
import cn.edu.xmu.wwf.privilege.utils.JWTUtils;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;
    @Autowired
    HttpServletRequest request;
    @PostMapping("/login")
    public ReturnObject login(@RequestBody LoginStaffVo loginStaffVo){
        return staffService.login(loginStaffVo);
    }
    @GetMapping("")
    public ReturnObject getStaff(@RequestParam int pageNum,@RequestParam int pageSize){
        String token=request.getHeader("token");
        String username=JWTUtils.getUserName(token);
        return staffService.getAllStaffInfo(pageNum,pageSize,username);
    }
    @GetMapping("/depart/{departId}")
    public ReturnObject getStaffInDepart(@RequestParam int pageNum,@RequestParam int pageSize,@PathVariable int departId){
        return staffService.getStaffInfoByDepartId(pageNum,pageSize,departId);
    }
    @GetMapping("/info")
    public ReturnObject getStaffInDepart(){
        String token=request.getHeader("token");
        String username=JWTUtils.getUserName(token);
        return staffService.getStaffRetVoByUsername(username);
    }
    @GetMapping("/ip")
    public ReturnObject getIp(){
        return new ReturnObject(request.getRemoteAddr());
    }
}
