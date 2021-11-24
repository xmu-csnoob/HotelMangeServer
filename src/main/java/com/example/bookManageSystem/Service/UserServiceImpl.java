package com.example.bookManageSystem.Service;

import com.example.bookManageSystem.Bean.Book;
import com.example.bookManageSystem.Bean.Isbn;
import com.example.bookManageSystem.Bean.Rental;
import com.example.bookManageSystem.Bean.User;
import com.example.bookManageSystem.Mapper.BookMapper;
import com.example.bookManageSystem.Mapper.IsbnMapper;
import com.example.bookManageSystem.Mapper.RentalMapper;
import com.example.bookManageSystem.Mapper.UserMapper;
import com.example.bookManageSystem.Tools.Encryptor;
import com.example.bookManageSystem.Tools.Format;
import com.example.bookManageSystem.Tools.Time;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    RentalMapper rentalMapper;
    @Autowired
    IsbnMapper isbnMapper;
    @Override
    public List<User> selectAll(){
        return userMapper.queryAllUsers();
    }
    @Override
    public int getUserNum(){return userMapper.queryUserNum();}
    @Override
    public String verifyLogin(User user){
        Encryptor encryptor =new Encryptor();
        user.setPassword(encryptor.encode(user.getPassword()));
        if(userMapper.queryUserByPassword(user.getUserName(),user.getPassword()).size()!=0)
            return "登录成功";
        else
            return "登录失败";
    }
    @Override
    public User getUserByUserName(String userName){
        return userMapper.queryUserByUserName(userName);
    }
    @Override
    public String verifyAlterPassword(User user){
        String msg=new Format().checkPasswordFormat(user.getNewPassword());
        if(!msg.equals("格式正确"))
            return msg;
        Encryptor encryptor = new Encryptor();
        User temp=userMapper.queryUserByUserName(user.getUserName());
        if(temp.getPassword().equals(encryptor.encode(user.getPassword())))
        {
            userMapper.updatePasswordByUserName(user.getUserName(), encryptor.encode(user.getNewPassword()));
            return "修改成功";
        }
        return "修改失败，密码错误";
    }
    @Override
    public String verifyAlterEmail(User user){
        String msg=new Format().checkEmailFormat(user.getNewEmail());
        if(!msg.equals("格式正确"))
            return msg;
        User temp=userMapper.queryUserByUserName(user.getUserName());
        if(temp.getEmail().equals(user.getEmail())){
            userMapper.updateEmailByUserName(user.getUserName(),user.getNewEmail());
            return "修改成功";
        }
        return "修改失败，邮箱错误";
    }
    @Override
    public String verifyAlterTel(User user){
        String msg=new Format().checkTelFormat(user.getNewTel());
        if(!msg.equals("格式正确"))
            return msg;
        User temp=userMapper.queryUserByUserName(user.getUserName());
        if(temp.getTel().equals(user.getTel())){
            userMapper.updateTelByUserName(user.getUserName(),user.getNewTel());
            return "修改成功";
        }
        return "修改失败，电话错误";
    }
    @Override
    public String insertUser(User user){                                //?格式判断？
        if(userMapper.queryUserByUserName(user.getUserName())==null)
        {
            Format format=new Format();
            String userID;
            while(true)
            {
                userID=format.generateID();
                if(userMapper.queryUserByUserID(userID).size()==0)
                    break;
            }
            //?
            userMapper.insertUser(userID,user.getUserName(),user.getTel(),user.getEmail());
            return userID;
        }
        return "添加失败，该用户名已存在";
    }
    @Override
    public String verifyRegister(User user) {
        if(userMapper.queryUserByUserName(user.getUserName())==null)
        {
            Format format=new Format();
            String userID;
            while(true)
            {
                userID=format.generateID();
                if(userMapper.queryUserByUserID(userID).size()==0)
                    break;
            }
            String msg=format.checkUserFormat(user);
            if(!msg.equals("格式正确"))
                return msg;
            Encryptor encryptor = new Encryptor();
            user.setPassword(encryptor.encode(user.getPassword()));
            userMapper.registerUser(userID,user.getUserName(),user.getPassword(),user.getTel(),user.getEmail());
            return "注册成功";
        }
        return "注册失败，该用户名已存在";
    }
    @Override
    public String deleteUser(User user){
        try{
            List<Book> list=bookMapper.queryLentBooks(user.getUserID());
            for(Book book:list){
                if(book.getReturnDate()==null)
                    return "删除失败，该用户有在借图书";
            }
            userMapper.deleteUser(user.getUserID());
            return "删除成功";
        }catch(Exception e){
            return "删除失败";
        }
    }
    @Override
    public String alterUser(User user){
        try{
            User temp = userMapper.queryUserByUserName(user.getUserName());
            if(temp==null||temp.getUserID().equals(user.getUserID())){
                String msg=new Format().checkUserFormat(user);
                if(!msg.equals("格式正确"))
                    return msg;
                userMapper.alterUser(user.getUserID(),user.getUserName(),user.getTel(),user.getEmail());
                return "修改成功";
            }
            else
                return "修改失败，新用户名已存在";
        }catch(Exception e){
            return "修改失败";
        }
    }
    @Override
    public double queryOverDueFine(String userID){
        double res=0;
        List<Book> list=bookMapper.queryLentBooks(userID);
        for(Book book:list){
            if(book.getReturnDate()!=null)
                continue;
            String dueDate=rentalMapper.getDueDateByPRIMARY(book.getBookID(),userID,rentalMapper.getLentDateByPRIMARY(book.getBookID(),userID));
            Time time=new Time();
            int days=time.dateDiffForDay(bookMapper.getCurrentDate(), dueDate);
            if(days>0)
                res+=days*book.getFinePerDay();
        }
        return res;
    }
    @Override
    public int getUserRentalNum(String userID){
        return userMapper.queryRentalNum(userID);
    }
    @Override
    public PageInfo<User> findAllUsersByPageS(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> lists =userMapper.queryAllUsers();
        for(User user:lists){
            user.setOverDueFine(queryOverDueFine(user.getUserID()));
        }
        return new PageInfo<>(lists);
    }
    @Override
    public PageInfo<User> findAllUsersByPageWithLimits(int pageNum, int pageSize){
        return null;
    }
    @Override
    public int getUserBreachNum(User user) {
        User temp = userMapper.queryUserByUserName(user.getUserName());
        return  userMapper.queryUserBreachNum(temp.getUserID());
    }
    @Override
    public int getUserRentalNum(User user) {
        User temp = userMapper.queryUserByUserName(user.getUserName());
        return  userMapper.queryUserRentalNum(temp.getUserID());
    }
    @Override
    public List<Rental> getUserBreach(String userName) {
        User user=userMapper.queryUserByUserName(userName);
        List<Rental> list=userMapper.queryUserBreach(user.getUserID());

        for(Rental rental:list){
            if(rental.getOverDueFine()==0&&rental.getReturnDate()==null){
                rental.setReturnDate("尚未归还");
                Time time=new Time();
                int days=time.dateDiffForDay(rentalMapper.getCurrentDate(),rental.getDueDate());
                if(days>0){
                    double finePerDay=bookMapper.queryFinePerDayByBookID(rental.getBookID());
                    rental.setOverDueFine(days*finePerDay);
                }
            }
        }
        return list;
    }
    @Override
    public List<Book> selectUserFinePaidRentalWithLimits(Book book) {
        User user=userMapper.queryUserByUserName(book.getUserName());
        List<Book> list=userMapper.selectUserFinePaidRentalByTimeAndUserID(user.getUserID(),book.getLentDate(),book.getDueDate());
        for(Book temp:list){
            Book instance=bookMapper.queryBookByBookID(temp.getBookID());
            Isbn isbn=isbnMapper.queryIsbnByIsbn(instance.getIsbn());
            //isbn
            temp.setBookName(isbn.getBookName());
            temp.setAuthor(isbn.getAuthor());
            //book
            temp.setUserName(userMapper.queryUserByUserID(temp.getUserID()).get(0).getUserName());
            temp.setKeepDays(instance.getKeepDays());
            temp.setFinePerDay(instance.getFinePerDay());
        }
        return list;
    }
    @Override
    public List<Book> selectAllUserFinePaidRentalWithLimits(Book book) {
        List<Book> list=userMapper.selectAllUserFinePaidRentalByTime(book.getLentDate(),book.getDueDate());
        for(Book temp:list){
            Book instance=bookMapper.queryBookByBookID(temp.getBookID());
            Isbn isbn=isbnMapper.queryIsbnByIsbn(instance.getIsbn());
            //isbn
            temp.setBookName(isbn.getBookName());
            temp.setAuthor(isbn.getAuthor());
            //book
            temp.setUserName(userMapper.queryUserByUserID(temp.getUserID()).get(0).getUserName());
            temp.setKeepDays(instance.getKeepDays());
            temp.setFinePerDay(instance.getFinePerDay());
        }
        return list;
    }
}
