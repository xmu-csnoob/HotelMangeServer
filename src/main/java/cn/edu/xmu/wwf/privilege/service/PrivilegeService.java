package cn.edu.xmu.wwf.privilege.service;

import cn.edu.xmu.wwf.privilege.dao.PrivilegeDao;
import cn.edu.xmu.wwf.privilege.model.bo.Privilege;
import cn.edu.xmu.wwf.privilege.model.po.PrivilegePo;
import cn.edu.xmu.wwf.privilege.model.vo.CreatePrivVo;
import cn.edu.xmu.wwf.privilege.model.vo.PrivilegeVo;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {
    @Autowired
    PrivilegeDao privilegeDao;
    public PrivilegeVo addPrivilege(CreatePrivVo createPrivVo){
        Privilege privilege=new Privilege();
        privilege.setName(createPrivVo.getName());
        privilege.setRoleId(createPrivVo.getRoleId());
        PrivilegePo privilegePo=privilegeDao.addPriv(privilege);
        PrivilegeVo createPrivRetVo=new PrivilegeVo();
        createPrivRetVo.setId(privilegePo.getId());
        createPrivRetVo.setName(privilegePo.getName());
        createPrivRetVo.setRoleId(privilegePo.getRoleId());
        return createPrivRetVo;
    }
    public ReturnObject getPrivilege(int id){
        ReturnObject<PrivilegePo> ret=privilegeDao.getPriv(id);
        if(ret.getData()==null)
            return ret;
        PrivilegePo privilegePo=privilegeDao.getPriv(id).getData();
        PrivilegeVo privilegeVo=new PrivilegeVo();
        privilegeVo.setId(id);
        privilegeVo.setRoleId(privilegePo.getRoleId());
        privilegeVo.setName(privilegePo.getName());
        return new ReturnObject(privilegeVo);
    }
    public ReturnObject deletePrivilege(int id){
        return privilegeDao.delPriv(id);
    }
}
