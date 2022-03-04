package cn.edu.xmu.wwf.privilege.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffMapper {
    String getPasswordByUsername(String username);
}
