package cn.edu.xmu.wwf.privilege.mapper;

import cn.edu.xmu.wwf.privilege.model.po.RolePo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    void insertRole(RolePo rolePo);
    RolePo getRolePoByPrimaryKey(int id);
    void deleteRole(int id);
}
