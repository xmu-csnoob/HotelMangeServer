package cn.edu.xmu.wwf.privilege.mapper;

import cn.edu.xmu.wwf.privilege.model.po.PrivilegePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrivilegeMapper {
    void insertPrivilege(PrivilegePo privilegePo);
    PrivilegePo selectPrivilegeByPrimaryKey(int id);
    List<PrivilegePo> selectPrivilegeByRoleId(int roleId);
    void deletePrivilege(int id);
}
