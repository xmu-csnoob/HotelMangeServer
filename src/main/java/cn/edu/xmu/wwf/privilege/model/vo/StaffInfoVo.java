package cn.edu.xmu.wwf.privilege.model.vo;

import cn.edu.xmu.wwf.privilege.model.po.StaffPo;
import lombok.Data;

@Data
public class StaffInfoVo {
    int id;
    String name;
    int departId;
    String position;
    String tel;
    String email;
    public StaffInfoVo(StaffPo staffPo){
        this.id=staffPo.getId();
        this.name=staffPo.getName();
        this.departId=staffPo.getDepartId();
        this.position=staffPo.getPosition();
        this.tel=staffPo.getTel();
        this.email=staffPo.getEmail();
    }
}
