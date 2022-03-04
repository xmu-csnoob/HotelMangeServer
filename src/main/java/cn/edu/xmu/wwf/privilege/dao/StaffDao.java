package cn.edu.xmu.wwf.privilege.dao;

import cn.edu.xmu.wwf.privilege.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDao {
    @Autowired
    StaffMapper staffMapper;
    public String getPassword(String username){
        return staffMapper.getPasswordByUsername(username);
    }
}
