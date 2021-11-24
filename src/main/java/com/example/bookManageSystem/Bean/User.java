package com.example.bookManageSystem.Bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class User implements Serializable {
    private String userID;
    private String password;
    private String userName;
    private String tel;
    private String email;
    private String newPassword;
    private String newTel;
    private String newEmail;
    private double overDueFine;
    public User(){}
    public User(String json){
        Map maps=(Map)JSON.parse(json);
        if(maps.get("userID")!=null)
            this.userID=(String)maps.get("userID");
        if(maps.get("password")!=null)
            this.password=(String)maps.get("password");
        if(maps.get("userName")!=null)
            this.userName=(String)maps.get("userName");
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
        if(maps.get("overDurFine")!=null)
            this.overDueFine=Double.parseDouble((String)maps.get("overDueFine"));
    }
}