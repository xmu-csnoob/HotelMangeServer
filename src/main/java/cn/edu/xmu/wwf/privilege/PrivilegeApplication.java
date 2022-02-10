package cn.edu.xmu.wwf.privilege;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.xmu.wwf.privilege.mapper")
public class PrivilegeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrivilegeApplication.class, args);
    }
}
