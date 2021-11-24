package com.example.bookManageSystem.Service;

import com.example.bookManageSystem.Bean.Book;
import com.example.bookManageSystem.Bean.Rental;
import com.example.bookManageSystem.Bean.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    //提供信息
    PageInfo<User> findAllUsersByPageS(int pageNum, int pageSize);
    PageInfo<User> findAllUsersByPageWithLimits(int pageNum, int pageSize);
    User getUserByUserName(String userName);
    List<User> selectAll();
    List<Rental> getUserBreach(String userName);
    List<Book> selectUserFinePaidRentalWithLimits(Book book);
    List<Book> selectAllUserFinePaidRentalWithLimits(Book book);
    int getUserNum();
    int getUserRentalNum(String userID);
    int getUserRentalNum(User user);     //查询某个用户(根据userID)的所有借阅记录的数目
    int getUserBreachNum(User user);
    double queryOverDueFine(String userID);
    //提供服务
    String verifyLogin(User user);
    String verifyRegister(User user);
    String verifyAlterPassword(User user);
    String verifyAlterEmail(User user);
    String verifyAlterTel(User user);
    String insertUser(User user);
    String deleteUser(User user);
    String alterUser(User user);
}
