package com.example.bookManageSystem.Controller;

import com.example.bookManageSystem.Bean.Book;
import com.example.bookManageSystem.Bean.User;
import com.example.bookManageSystem.Service.BookService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    //提供信息
    @RequestMapping("/bookNum")
    public int bookNum(){
        return bookService.getBookNum();
    }
    @RequestMapping("/breachNum")
    public int breachNum(){
        return bookService.queryBreachNum();
    }
    @GetMapping("/getPage")
    public PageInfo<Book> testPageHelper1(@RequestParam int pageNum){
        return bookService.findAllBooksByPageS(pageNum, 3);
    }
    @RequestMapping("/getPageWithLimits")
    public PageInfo<Book> getPageWithLimits(@RequestBody Book book,@RequestParam int pageNum){
        return bookService.findAllBooksByPageWithLimits(book,pageNum,3);
    }
    @RequestMapping("/lent")
    public PageInfo<Book> lentBook(@RequestBody User user,@RequestParam int pageNum) {
        return bookService.selectLentBook(user.getUserName(),pageNum,3);}
    @RequestMapping("/finePerDay")
    public double finePerDay(@RequestBody Book book){ return bookService.getFinePerDayByBookID(book.getBookID()); }
    //提供事件响应
    @RequestMapping("/insert")
    public String insertBook(@RequestBody Book book){
        return bookService.insertBook(book);
    }
    @RequestMapping("/delete")
    public String deleteBook(@RequestBody Book book){
        return bookService.verifyDeleteBook(book.getBookID());
    }
    @RequestMapping("/alter")
    public String alterBook(@RequestBody Book book){
        return bookService.alterBook(book);
    }
    @RequestMapping("/lendBook")
    public String lendBook(@RequestBody Book book) { return bookService.lendBook(book); }
    @RequestMapping("/returnBook")
    public String returnBook(@RequestBody Book book) { return bookService.returnBook(book); }
    @RequestMapping("/relendBook")
    public String relendBook(@RequestBody Book book){ return bookService.relendBook(book); }
    @RequestMapping("/queryBook")
    public Book queryBookInfo(@RequestParam String bookID){
        System.out.println(bookService.getBookByID(bookID));
        return bookService.getBookByID(bookID);
    }
}
