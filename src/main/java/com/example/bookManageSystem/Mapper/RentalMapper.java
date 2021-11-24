package com.example.bookManageSystem.Mapper;

import com.example.bookManageSystem.Bean.Rental;
import com.example.bookManageSystem.Bean.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RentalMapper {
    int queryRentalNum();
    List<Rental> queryAllRentals();
    List<Rental> queryAllBreach();
    List<Rental> queryRentalByRentalID(String rentalID);
    String getDueDateByPRIMARY(String bookID,String userID,String lentDate);
    String getLentDateByPRIMARY(String bookID,String userID);
    //Only For Tool Use:获取当前日期
    String getCurrentDate();
    User getUserByBookID(String bookID);
    List<Rental> queryRentalByBookID(String bookID);
    void insertLendBookInfo(String rentalID,String bookID,String userID,String lentDate,String dueDate);
    void updateLentBookInfo(String rentalID,String returnDate,double overDueFine);
    //将userID、bookID和returnDate为空作为条件，查询rental
    Rental queryRental(String userID,String bookID);
}
