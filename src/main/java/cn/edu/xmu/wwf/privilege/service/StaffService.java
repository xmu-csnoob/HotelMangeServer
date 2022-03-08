package cn.edu.xmu.wwf.privilege.service;

import cn.edu.xmu.wwf.privilege.dao.StaffDao;
import cn.edu.xmu.wwf.privilege.mapper.StaffMapper;
import cn.edu.xmu.wwf.privilege.model.po.StaffPo;
import cn.edu.xmu.wwf.privilege.model.vo.LoginStaffVo;
import cn.edu.xmu.wwf.privilege.model.vo.StaffInfoVo;
import cn.edu.xmu.wwf.privilege.model.vo.StaffLoginRetVo;
import cn.edu.xmu.wwf.privilege.utils.JWTUtils;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService {
    @Autowired
    StaffDao staffDao;
    public ReturnObject<StaffLoginRetVo> login(LoginStaffVo loginStaffVo){
        String userPassword=loginStaffVo.getPassword();
        String realPassword=staffDao.getPassword(loginStaffVo.getUsername());
        StaffLoginRetVo staffLoginRetVo=new StaffLoginRetVo();
        if(userPassword.equals(realPassword)){
            staffLoginRetVo.setSuccess(true);
            staffLoginRetVo.setToken(JWTUtils.createToken(loginStaffVo.getUsername()).substring(7));
            return new ReturnObject<StaffLoginRetVo>(staffLoginRetVo);
        }else{
            staffLoginRetVo.setSuccess(false);
            return new ReturnObject<StaffLoginRetVo>(staffLoginRetVo);
        }
    }
    public ReturnObject<PageInfo<StaffInfoVo>> getAllStaffInfo(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<StaffPo> staffPoList=staffDao.getAllStaff();
        return getPageInfoReturnObject(pageSize, staffPoList);
    }
    public ReturnObject<PageInfo<StaffInfoVo>> getStaffInfoByDepartId(int pageNum,int pageSize,int departId){
        PageHelper.startPage(pageNum,pageSize);
        List<StaffPo> staffPoList=staffDao.getStaffByDepartId(departId);
        return getPageInfoReturnObject(pageSize, staffPoList);
    }

    private ReturnObject<PageInfo<StaffInfoVo>> getPageInfoReturnObject(int pageSize, List<StaffPo> staffPoList) {
        List<StaffInfoVo> staffInfoVos=new ArrayList<>();
        for(StaffPo staffPo:staffPoList){
            StaffInfoVo staffInfoVo=new StaffInfoVo(staffPo);
            staffInfoVos.add(staffInfoVo);
        }
        PageInfo<StaffInfoVo> pageInfo=new PageInfo<>(staffInfoVos);
        int staffNum=staffDao.getStaffNum();
        if(staffNum%pageSize==0){
            pageInfo.setPages(staffDao.getStaffNum()/pageSize);
        }else{
            pageInfo.setPages(staffDao.getStaffNum()/pageSize+1);
        }
        return new ReturnObject<>(pageInfo);
    }
}
