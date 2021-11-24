package com.example.bookManageSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.bookManageSystem.Mapper")
public class BookManageSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookManageSystemApplication.class, args);
    }
}
