package cn.edu.xmu.wwf.privilege.model.vo;

import cn.edu.xmu.wwf.privilege.model.po.StaffPo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StaffRetVo {
    String username;
    int level;
    String position;
    String tel;
    String email;
    public StaffRetVo(StaffPo staffPo){
        this.username=staffPo.getName();
        this.level=staffPo.getLevel();
        this.position=staffPo.getPosition();
        this.tel=staffPo.getTel();
        this.email=staffPo.getEmail();
    }
}
