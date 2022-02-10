package cn.edu.xmu.wwf.privilege.dao;

import cn.edu.xmu.wwf.privilege.mapper.PrivilegeMapper;
import cn.edu.xmu.wwf.privilege.model.bo.Privilege;
import cn.edu.xmu.wwf.privilege.model.po.PrivilegePo;
import cn.edu.xmu.wwf.privilege.utils.ReturnNo;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PrivilegeDao {
    @Autowired
    PrivilegeMapper privilegeMapper;
    public PrivilegePo addPriv(Privilege privilege){
        PrivilegePo privilegePo=new PrivilegePo();
        privilegePo.setRoleId(privilege.getRoleId());
        privilegePo.setName(privilege.getName());
        privilegeMapper.insertPrivilege(privilegePo);
        return privilegePo;
    }
    public ReturnObject<PrivilegePo> getPriv(int id){
        PrivilegePo privilegePo=privilegeMapper.selectPrivilegeByPrimaryKey(id);
        if(privilegePo==null)
            return new ReturnObject<PrivilegePo>(ReturnNo.RES0URCE_NOT_FOUND,"权限id不存在");
        return new ReturnObject<PrivilegePo>(privilegeMapper.selectPrivilegeByPrimaryKey(id));
    }
    public ReturnObject delPriv(int id){
        PrivilegePo privilegePo=privilegeMapper.selectPrivilegeByPrimaryKey(id);
        if(privilegePo==null)
            return new ReturnObject<PrivilegePo>(ReturnNo.RES0URCE_NOT_FOUND,"权限id不存在");
        privilegeMapper.deletePrivilege(id);
        return new ReturnObject(ReturnNo.OK);
    }
}
