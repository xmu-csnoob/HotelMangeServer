package com.example.bookManageSystem.Mapper;

import com.example.bookManageSystem.Bean.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookMapper {
    //查询所有图书信息
    List<Book> queryAllBooks();
    //查询图书数量
    int queryBookNum();
    //查询违约数量
    int queryBreachNum();
    //插入新的book记录
    void insertBook(String bookID, String isbn, int keepDays, double finePerDay);
    //根据bookID删除book记录
    void deleteBook(String bookID);
    //根据bookID修改book记录的字段
    void alterBook(String bookID, int keepDays, double finePerDay);
    //根据bookID查询每日罚款
    double queryFinePerDayByBookID(String bookID);
    //Only For Tool Use:获取当前日期
    String getCurrentDate();
    //根据用户名查询该用户所借阅图书信息
    List<Book> queryLentBooks(String userID);
    //根据索书号、书名、作者、出版社、isbn进行书籍的模糊查询
    List<Book> select(String bookID, String bookName, String author, String publisher, String isbn, double leastPrice, double largestPrice);
    //根据bookID借书
    void lendBook(String bookID);
    //根据bookID还书
    void returnBook(String bookID);
    //根据索书号查询图书
    Book queryBookByBookID(String bookID);
}
