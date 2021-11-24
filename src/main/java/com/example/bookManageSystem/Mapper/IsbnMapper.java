package com.example.bookManageSystem.Mapper;

import com.example.bookManageSystem.Bean.Isbn;
import org.springframework.stereotype.Component;

@Component
public interface IsbnMapper {
    //根据ISBN修改isbn记录的字段
    void alterIsbn(String oldIsbn, String newIsbn, String bookName, String author, String publisher, double price, String introduction);
    //插入新的isbn记录
    void insertIsbn(String isbn, String bookName, String author, String publisher, double price, String introduction);
    Isbn queryIsbnByIsbn(String isbn);
}
