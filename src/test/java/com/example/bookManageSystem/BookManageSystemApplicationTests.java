package com.example.bookManageSystem;

import com.example.bookManageSystem.Tools.Time;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookManageSystemApplicationTests {
    @Test
    void contextLoads() {
        Time time=new Time();
        System.out.println(time.dateAddDay("2021-7-3", 28));
    }
}
