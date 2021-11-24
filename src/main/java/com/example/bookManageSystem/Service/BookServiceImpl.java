package com.example.bookManageSystem.Service;

import com.example.bookManageSystem.Bean.Book;
import com.example.bookManageSystem.Bean.Isbn;
import com.example.bookManageSystem.Bean.Rental;
import com.example.bookManageSystem.Bean.User;
import com.example.bookManageSystem.Mapper.BookMapper;
import com.example.bookManageSystem.Mapper.IsbnMapper;
import com.example.bookManageSystem.Mapper.RentalMapper;
import com.example.bookManageSystem.Mapper.UserMapper;
import com.example.bookManageSystem.Tools.Format;
import com.example.bookManageSystem.Tools.Time;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookMapper bookMapper;
    @Autowired
    IsbnMapper isbnMapper;
    @Autowired
    RentalMapper rentalMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public List<Book> selectAll(){
        List<Book> bookList=bookMapper.queryAllBooks();
        for(Book book:bookList){
            if(book.getIfLent()!=null&&book.getIfLent().equals("0")){
                book.setUserID("尚未借出");
                book.setLentDate("尚未借出");
                book.setDueDate("尚未借出");
            }
            else
            {
                book.setUserID(rentalMapper.getUserByBookID(book.getBookID()).getUserID());
                Time time=new Time();
                book.setDueDate(time.dateAddDay(rentalMapper.getLentDateByPRIMARY(book.getBookID(),book.getUserID()),book.getKeepDays()));
            }
        }
        return bookList;
    }
    @Override
    public int getBookNum(){ return bookMapper.queryBookNum(); }
    @Override
    public String insertBook(Book book){
        try{
            isbnMapper.insertIsbn(book.getIsbn(), book.getBookName(), book.getAuthor(), book.getPublisher(), book.getPrice(), book.getIntroduction());
        }catch (Exception e){
            //"ISBN添加失败，ISBN中已有相应条目";
        }
        try {
            bookMapper.insertBook(book.getBookID(), book.getIsbn(), book.getKeepDays(), book.getFinePerDay());
            return "添加成功";
        }catch(Exception e){
            return "添加失败";
        }
    }
    @Override
    public String verifyDeleteBook(String bookID){
        try{
            bookMapper.deleteBook(bookID);
            return "删除成功";
        }catch(Exception e){
            return "删除失败";
        }
    }
    @Override
    public String alterBook(Book book){
        try{
            Book temp=bookMapper.queryBookByBookID(book.getBookID());
            isbnMapper.alterIsbn(temp.getIsbn(),book.getIsbn(),book.getBookName(),book.getAuthor(),book.getPublisher(),book.getPrice(),book.getIntroduction());
            bookMapper.alterBook(book.getBookID(),book.getKeepDays(),book.getFinePerDay());
            return "修改成功";
        }catch(Exception e){
            return "修改失败";
        }
    }
    @Override
    public int queryBreachNum(){
        return bookMapper.queryBreachNum();
    }
    @Override
    public double getFinePerDayByBookID(String bookID){
        return bookMapper.queryFinePerDayByBookID(bookID);
    }
    @Override
    public String getCurrentDate(){
        return bookMapper.getCurrentDate();
    }
    @Override
    public PageInfo<Book> findAllBooksByPageS(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Book> lists = bookMapper.queryAllBooks();
        for (Book book:lists)
        {
            if(book.getIfLent()!=null){
                User user=rentalMapper.getUserByBookID(book.getBookID());
                book.setUserID(user.getUserID());
                book.setUserName(user.getUserName());
            }
            else{
                book.setIfLent("0");
                book.setUserID("未借出");
                book.setLentDate("未借出");
                book.setDueDate("未借出");
                continue;
            }
            book.setLentDate(rentalMapper.getLentDateByPRIMARY(book.getBookID(),book.getUserID()));
            book.setDueDate(rentalMapper.getDueDateByPRIMARY(book.getBookID(),book.getUserID(),book.getLentDate()));
        }
        return new PageInfo<>(lists);
    }
    @Override
    public PageInfo<Book> findAllBooksByPageWithLimits(Book instance, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Book> lists=bookMapper.select(instance.getBookID(),instance.getBookName(),instance.getAuthor(),instance.getPublisher(),instance.getIsbn(),instance.getLeastPrice(),instance.getLargestPrice());
        for (Book book:lists)
        {
            if(book.getIfLent()!=null)
            {
                User user=rentalMapper.getUserByBookID(book.getBookID());
                book.setUserName(user.getUserName());
                book.setUserID(user.getUserID());
            }
            else{
                book.setIfLent("0");
                book.setUserID("未借出");
                book.setLentDate("未借出");
                book.setDueDate("未借出");
                continue;
            }
            book.setLentDate(rentalMapper.getLentDateByPRIMARY(book.getBookID(),book.getUserID()));
            book.setDueDate(rentalMapper.getDueDateByPRIMARY(book.getBookID(),book.getUserID(),book.getLentDate()));
        }
        return new PageInfo<>(lists);
    }
    @Override
    public PageInfo<Book> selectLentBook(String userName,int pageNum,int pageSize) {
        User user=userMapper.queryUserByUserName(userName);
        List<Book> lentInfoList=bookMapper.queryLentBooks(user.getUserID());
        for(Book book:lentInfoList) {
            Book temp=bookMapper.queryBookByBookID(book.getBookID());
            Isbn isbn=isbnMapper.queryIsbnByIsbn(book.getIsbn());
            //isbn
            book.setBookName(isbn.getBookName());
            book.setAuthor(isbn.getAuthor());
            book.setPublisher(isbn.getPublisher());
            book.setPrice(isbn.getPrice());
            book.setIntroduction(isbn.getIntroduction());
            //book
            book.setKeepDays(temp.getKeepDays());
            book.setIfLent(temp.getIfLent());
            book.setFinePerDay(temp.getFinePerDay());
            book.setUserName(userName);
            if(book.getReturnDate()==null) {
                Time time = new Time();
                int days = time.dateDiffForDay(rentalMapper.getCurrentDate(), book.getDueDate());
                double overDueFine = 0;
                if (days > 0){
                    double finePerDay = bookMapper.queryFinePerDayByBookID(book.getBookID());
                    overDueFine = days * finePerDay;
                }
                book.setOverDueFine(overDueFine);
            }
            //book.getReturnDate()!=null的情况，overDueFine已经由rental中相应overDueFine赋值
        }
        return new PageInfo<>(lentInfoList);
    }
    @Override
    public String lendBook(Book book) {
        try{
            User user = userMapper.queryUserByUserName(book.getUserName());
            if(userMapper.queryHaveOverDueFineRentalByUserID(user.getUserID()).size()!=0)
                return "借书失败，尚有罚款未缴纳";
            if(rentalMapper.getUserByBookID(book.getBookID())!=null)
                return "该书已经借出！";
            Book temp = bookMapper.queryBookByBookID(book.getBookID());
            bookMapper.lendBook(temp.getBookID());
            Format format=new Format();
            String rentalID;
            while(true)
            {
                rentalID=format.generateID();
                if(rentalMapper.queryRentalByRentalID(rentalID).size()==0)
                    break;
            }
            Time time=new Time();
            rentalMapper.insertLendBookInfo(rentalID,temp.getBookID(),user.getUserID()
                    ,bookMapper.getCurrentDate(),time.dateAddDay(bookMapper.getCurrentDate(),temp.getKeepDays()));
            return "借书成功";
        }catch(Exception e){
            return "借书失败";
        }
    }
    @Override
    public String returnBook(Book book) {
        try{
            bookMapper.returnBook(book.getBookID());
            User user=userMapper.queryUserByUserName(book.getUserName());
            Rental rental=rentalMapper.queryRental(user.getUserID(),book.getBookID());
            Time time=new Time();
            int days=time.dateDiffForDay(rentalMapper.getCurrentDate(), rental.getDueDate());
            double overDueFine=0;
            if(days>0){
                double finePerDay=bookMapper.queryFinePerDayByBookID(book.getBookID());
                overDueFine=days*finePerDay;
            }
            rentalMapper.updateLentBookInfo(rental.getRentalID(),bookMapper.getCurrentDate(),overDueFine);
            if(overDueFine>0)
                return "还书成功，罚款已缴纳";
            return "还书成功";
        }catch(Exception e){
            return "还书失败";
        }
    }
    @Override
    public String relendBook(Book book){
        try{
            String info=returnBook(book);
            lendBook(book);
            if(info.equals("还书成功，罚款已缴纳"))
                return "续借成功，罚款已缴纳";
            return "续借成功";
        }catch(Exception e){
            return "续借失败";
        }
    }
    @Override
    public Book getBookByID(String bookID){
        System.out.println(bookMapper.queryBookByBookID(bookID));
        return bookMapper.queryBookByBookID(bookID);
    }
}
