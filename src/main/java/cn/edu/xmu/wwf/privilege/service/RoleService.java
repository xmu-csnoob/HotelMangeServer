package cn.edu.xmu.wwf.privilege.service;

import cn.edu.xmu.wwf.privilege.dao.RoleDao;
import cn.edu.xmu.wwf.privilege.model.bo.Role;
import cn.edu.xmu.wwf.privilege.model.po.RolePo;
import cn.edu.xmu.wwf.privilege.model.vo.CreateRoleVo;
import cn.edu.xmu.wwf.privilege.model.vo.RoleVo;
import cn.edu.xmu.wwf.privilege.utils.ReturnNo;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;
    public ReturnObject<RoleVo> addRole(CreateRoleVo createRoleVo){
        ReturnObject<RolePo> returnObject=roleDao.addRole(createRoleVo);
        if(returnObject.getData()==null){
            return new ReturnObject<RoleVo>(ReturnNo.INTERNAL_SERVER_ERROR,"服务器内部错误");
        }else{
            RolePo rolePo=returnObject.getData();
            RoleVo roleVo=new RoleVo();
            roleVo.setId(rolePo.getId());
            roleVo.setDepartId(rolePo.getDepartId());
            return new ReturnObject<>(roleVo);
        }
    }
}
