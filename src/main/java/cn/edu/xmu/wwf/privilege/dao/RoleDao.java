package cn.edu.xmu.wwf.privilege.dao;

import cn.edu.xmu.wwf.privilege.mapper.RoleMapper;
import cn.edu.xmu.wwf.privilege.model.po.RolePo;
import cn.edu.xmu.wwf.privilege.model.vo.CreateRoleVo;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {
    @Autowired
    RoleMapper roleMapper;
    public ReturnObject<RolePo> addRole(CreateRoleVo createRoleVo){
        RolePo rolePo=new RolePo();
        rolePo.setDepartId(createRoleVo.getDepartId());
        rolePo.setName(createRoleVo.getName());
        roleMapper.insertRole(rolePo);
        return new ReturnObject<RolePo>(rolePo);
    }
}
