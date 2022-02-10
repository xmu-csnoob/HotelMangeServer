package cn.edu.xmu.wwf.privilege.model.po;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class RolePo {
    @Min(0)
    int id;
    @Min(0)
    int departId;
}
