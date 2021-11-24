package com.example.bookManageSystem.Mapper;

import com.example.bookManageSystem.Bean.Book;
import com.example.bookManageSystem.Bean.Rental;
import com.example.bookManageSystem.Bean.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    List<User> queryAllUsers();
    List<User> queryUserByUserID(String userID);
    List<User> queryUserByPassword(String userName,String password);
    List<Rental> queryUserBreach(String userID);
    List<Rental> queryHaveOverDueFineRentalByUserID(String userID);
    //根据起始终止时间查询所有用户的违章缴款记录
    List<Book> selectAllUserFinePaidRentalByTime(String startDate, String endDate);
    //根据userID和起始终止时间查询用户的违章缴款记录
    List<Book> selectUserFinePaidRentalByTimeAndUserID(String userID,String startDate,String endDate);
    User queryUserByUserName(String userName);
    int queryUserNum();
    int queryRentalNum(String userID);
    int queryUserBreachNum(String userID);
    int queryUserRentalNum(String userID);
    void updatePasswordByUserName(String userName,String newPassword);
    void updateEmailByUserName(String userName,String newEmail);
    void updateTelByUserName(String userName,String newTel);
    void insertUser(String userID,String userName,String tel,String email);
    void registerUser(String userID,String userName,String password,String tel,String email);
    void deleteUser(String userID);
    void alterUser(String userID,String userName,String tel,String email);
    double queryOverDueFineByID(String userID);
    //Only For Tool Use:获取当前日期
    String getCurrentDate();
}
