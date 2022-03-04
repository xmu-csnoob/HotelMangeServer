package cn.edu.xmu.wwf.privilege.model.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CreateRoleVo {
    @NotBlank
    String name;
    @Min(0)
    int departId;
}
