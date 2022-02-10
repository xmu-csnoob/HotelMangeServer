package cn.edu.xmu.wwf.privilege.model.po;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class PrivilegePo {
    @Min(0)
    int id;
    @Min(0)
    int roleId;
    @NotBlank
    String name;
}
