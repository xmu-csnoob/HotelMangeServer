package cn.edu.xmu.wwf.privilege.mapper;

import cn.edu.xmu.wwf.privilege.model.po.StaffPo;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StaffMapper {
    String getPasswordByUsername(String username);
    List<StaffPo> selectAll();
    List<StaffPo> selectByDepartId(int departId);
    int getStaffNum();
    int getStaffNumByDepartId(int departId);
    int updateLastLoginTime(String username);
}
