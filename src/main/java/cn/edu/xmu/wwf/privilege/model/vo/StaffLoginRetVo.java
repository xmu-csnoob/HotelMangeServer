package cn.edu.xmu.wwf.privilege.model.vo;

import lombok.Data;

@Data
public class StaffLoginRetVo {
    boolean isSuccess;
    int departId;
    int name;
    String token;
}
