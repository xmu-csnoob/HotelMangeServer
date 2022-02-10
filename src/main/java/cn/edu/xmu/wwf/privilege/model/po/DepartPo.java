package cn.edu.xmu.wwf.privilege.model.po;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class DepartPo {
    @Min(0)
    int id;
    @NotBlank
    String departName;
    String document;
    @Min(0)
    double leastSalary;
    double maxSalary;
    @Length(max = 11,min = 11)
    String tel;
}
