package cn.edu.xmu.wwf.privilege.dao;

import cn.edu.xmu.wwf.privilege.mapper.StaffMapper;
import cn.edu.xmu.wwf.privilege.model.po.StaffPo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDao {
    @Autowired
    StaffMapper staffMapper;
    public String getPassword(String username){
        return staffMapper.getPasswordByUsername(username);
    }
    public List<StaffPo> getAllStaff(int pageNum,int pageSize,int departId,int level){
        PageHelper.startPage(pageNum,pageSize);
        if(departId==20)
            return staffMapper.selectAll();
        return staffMapper.selectWithLimit(departId,level);
    }
    public int getStaffNum(){
        return staffMapper.getStaffNum();
    }
    public List<StaffPo> getStaffByDepartId(int departId){
        return staffMapper.selectByDepartId(departId);
    }
    public int getStaffNumByDepartId(int departId){
        return staffMapper.getStaffNumByDepartId(departId);
    }
    public StaffPo getStaffByUsername(String username){
        return staffMapper.getStaffByUsername(username);
    }
}
