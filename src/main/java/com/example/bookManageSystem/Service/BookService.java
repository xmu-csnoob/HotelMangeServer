package com.example.bookManageSystem.Service;

import com.example.bookManageSystem.Bean.Book;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BookService {
    //提供信息
    PageInfo<Book> findAllBooksByPageS(int pageNum, int pageSize);
    PageInfo<Book> selectLentBook(String userName,int pageNum,int pageSize);
    PageInfo<Book> findAllBooksByPageWithLimits(Book book,int pageNum, int pageSize);
    List<Book> selectAll();
    int getBookNum();
    int queryBreachNum();
    double getFinePerDayByBookID(String bookID);
    String getCurrentDate();
    //提供服务
    String insertBook(Book book);
    String verifyDeleteBook(String bookID);
    String alterBook(Book book);
    String lendBook(Book book);
    String returnBook(Book book);
    String relendBook(Book book);
    Book getBookByID(String bookID);
}
