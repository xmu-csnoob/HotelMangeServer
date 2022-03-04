package cn.edu.xmu.wwf.privilege.controller;

import cn.edu.xmu.wwf.privilege.model.vo.CreateRoleVo;
import cn.edu.xmu.wwf.privilege.model.vo.RoleVo;
import cn.edu.xmu.wwf.privilege.service.RoleService;
import cn.edu.xmu.wwf.privilege.utils.ReturnNo;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @PostMapping()
    public ReturnObject<RoleVo> createRole(@RequestBody CreateRoleVo createRoleVo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ReturnObject<>(ReturnNo.DATA_FORMAT_ERR0R,"数据格式错误");
        }
        return roleService.addRole(createRoleVo);
    }
}
