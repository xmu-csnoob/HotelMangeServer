package cn.edu.xmu.wwf.privilege.model.po;

import lombok.Data;

@Data
public class StaffPo {
    int id;
    String name;
    int departId;
    int level;
    String password;
    String position;
    String tel;
    String email;
}
