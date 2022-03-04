package cn.edu.xmu.wwf.privilege.service;

import cn.edu.xmu.wwf.privilege.dao.StaffDao;
import cn.edu.xmu.wwf.privilege.model.vo.LoginStaffVo;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    @Autowired
    StaffDao staffDao;
    public ReturnObject<Boolean> login(LoginStaffVo loginStaffVo){
        String userPassword=loginStaffVo.getPassword();
        String realPassword=staffDao.getPassword(loginStaffVo.getUsername());
        if(userPassword.equals(realPassword)){
            return new ReturnObject<Boolean>(true);
        }else{
            return new ReturnObject<Boolean>(false);
        }
    }
}
