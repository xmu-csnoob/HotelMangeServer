package com.example.bookManageSystem.Controller;

import com.example.bookManageSystem.Bean.Book;
import com.example.bookManageSystem.Bean.Rental;
import com.example.bookManageSystem.Bean.User;
import com.example.bookManageSystem.Service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;
    //提供信息
    @GetMapping("/getPage")
    public PageInfo<User> testPageHelper1(@RequestParam int pageNum){
        return userService.findAllUsersByPageS(pageNum, 10);
    }
    @RequestMapping("/userInfo")
    public User getInfoByName(@RequestParam("userName")String userName){ return userService.getUserByUserName(userName); }
    @RequestMapping("/userBreach")
    public List<Rental> getUserBreach(@RequestBody User user){ return userService.getUserBreach(user.getUserName()); }
    @RequestMapping("/userNum")
    public int userNum(){
        return userService.getUserNum();
    }
    @RequestMapping("/userBreachNum")
    public int userBreachNum(@RequestBody User user){ return userService.getUserBreachNum(user); }
    @RequestMapping("/userRentalNum")
    public int userRentalNum(@RequestBody User user){ return userService.getUserRentalNum(user); }
    @RequestMapping("/allFinePaidRentalWithLimits")
    public List<Book> allFinePaidRental(@RequestBody Book book){ return userService.selectAllUserFinePaidRentalWithLimits(book); }
    @RequestMapping("/finePaidRentalWithLimits")
    public List<Book> finePaidRental(@RequestBody Book book){ return userService.selectUserFinePaidRentalWithLimits(book); }
    //提供事件响应
    @RequestMapping(value="/login")
    public String login(@RequestBody User user){ return userService.verifyLogin(user); }
    @RequestMapping(value="/register")
    public String register(@RequestBody User user){ return userService.verifyRegister(user); }
    @RequestMapping("/alterPassword")
    public String alterPwd(@RequestBody User user){ return userService.verifyAlterPassword(user); }
    @RequestMapping("/alterEmail")
    public String alterEmail(@RequestBody User user){ return userService.verifyAlterEmail(user); }
    @RequestMapping("/alterTel")
    public String alterTel(@RequestBody User user){return userService.verifyAlterTel(user);}
    @RequestMapping("/insert")
    public String insertUser(@RequestBody User user){ return userService.insertUser(user); }
    @RequestMapping("/delete")
    public String deleteUser(@RequestBody User user){ return userService.deleteUser(user); }
    @RequestMapping("/alter")
    public String alter(@RequestBody User user){ return userService.alterUser(user); }
}
