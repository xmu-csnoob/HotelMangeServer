package cn.edu.xmu.wwf.privilege.controller;

import cn.edu.xmu.wwf.privilege.model.vo.CreatePrivVo;
import cn.edu.xmu.wwf.privilege.model.vo.PrivilegeVo;
import cn.edu.xmu.wwf.privilege.service.PrivilegeService;
import cn.edu.xmu.wwf.privilege.utils.ReturnNo;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {
    /**
     * Depart:
     * 1.部门 增删改查（4） 暂时不需要redis||rocketMQ
     * 2.角色挂载到部门
     * 3.从部门中删除角色
     *
     * Role:
     * 1.角色 增删改查（4） 暂时不需要redis||rocketMQ
     * 2.权限挂载到角色
     * 3.删除角色权限
     *
     * Privilege:
     * 1.权限增删改查 需要redis
     * todo:补充API
     * todo:redis存取
     */
    @Autowired
    PrivilegeService privilegeService;
    /**
     * todo:补充各类错误处理
     */
    @PostMapping(value="",produces = "application/json;charset=UTF-8")
    public ReturnObject<PrivilegeVo> createPrivilege(@RequestBody CreatePrivVo createPrivVo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ReturnObject<>(ReturnNo.DATA_FORMAT_ERR0R,"数据格式错误");
        }
        return new ReturnObject<PrivilegeVo>(privilegeService.addPrivilege(createPrivVo));
    }
    @GetMapping(value = "/{id}",produces = "application/json;charset=UTF-8")
    public ReturnObject getPrivilege(@PathVariable int id){
        ReturnObject returnObject=privilegeService.getPrivilege(id);
        if(returnObject.getData()==null){
            return returnObject;
        }
        return new ReturnObject(privilegeService.getPrivilege(id).getData());
    }
    @DeleteMapping(value="/{id}",produces = "application/json;charset=UTF-8")
    public ReturnObject<PrivilegeVo> delPrivilege(@PathVariable int id){
        return privilegeService.deletePrivilege(id);
    }
}
