package com.example.bookManageSystem.Bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Map;

@Data
public class Admin{
    private String adminID;
    private String password;
    private String adminName;
    private String tel;
    private String email;
    private String newPassword;
    private String newTel;
    private String newEmail;
    public Admin(){}
    public Admin(String json){
        Map maps=(Map) JSON.parse(json);
        if(maps.get("adminID")!=null)
            this.adminID=(String)maps.get("adminID");
        if(maps.get("password")!=null)
            this.password=(String)maps.get("password");
        if(maps.get("adminName")!=null)
            this.adminName=(String)maps.get("adminName");
        if(maps.get("tel")!=null)
            this.tel=(String)maps.get("tel");
        if(maps.get("email")!=null)
            this.email=(String)maps.get("email");
        if(maps.get("newPassword")!=null)
            this.newPassword=(String)maps.get("newPassword");
        if(maps.get("newTel")!=null)
            this.newTel=(String)maps.get("newTel");
        if(maps.get("newEmail")!=null)
            this.newEmail=(String)maps.get("newEmail");
    }
}
