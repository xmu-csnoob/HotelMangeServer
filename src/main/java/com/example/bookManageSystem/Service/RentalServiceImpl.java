package com.example.bookManageSystem.Service;

import com.example.bookManageSystem.Bean.Rental;
import com.example.bookManageSystem.Bean.User;
import com.example.bookManageSystem.Mapper.BookMapper;
import com.example.bookManageSystem.Mapper.RentalMapper;
import com.example.bookManageSystem.Mapper.UserMapper;
import com.example.bookManageSystem.Tools.Time;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService{
    @Autowired
    RentalMapper rentalMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public int getRentalNum(){
        return rentalMapper.queryRentalNum();
    }
    @Override
    public int getBreachNum(){
        return 0;
    }
    @Override
    public List<Rental> selectAll(){
        List<Rental> list=rentalMapper.queryAllRentals();
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
    public List<Rental> getAllBreach(){
        List<Rental> list=rentalMapper.queryAllBreach();
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
    public PageInfo<Rental> findAllRentalsByPageS(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Rental> lists =rentalMapper.queryAllRentals();
        for(Rental rental:lists){
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
        return new PageInfo<>(lists);
    }
    @Override
    public PageInfo<Rental> findAllBreachByPageS(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Rental> lists =rentalMapper.queryAllBreach();
        for(Rental rental:lists){
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
        return new PageInfo<>(lists);
    }
    @Override
    public User getUserByBookID(String bookID){
        return rentalMapper.getUserByBookID(bookID);
    }
}
