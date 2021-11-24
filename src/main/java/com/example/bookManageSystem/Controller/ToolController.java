package com.example.bookManageSystem.Controller;

import com.example.bookManageSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("")
public class ToolController {
    @Autowired
    BookService bookService;
    @RequestMapping("/time")
    public String getDate(){
        return bookService.getCurrentDate();
    }
}
