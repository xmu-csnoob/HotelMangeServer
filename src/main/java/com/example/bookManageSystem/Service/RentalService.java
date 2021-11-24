package com.example.bookManageSystem.Service;

import com.example.bookManageSystem.Bean.Rental;
import com.example.bookManageSystem.Bean.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RentalService {
    int getRentalNum();
    int getBreachNum();
    List<Rental> selectAll();
    List<Rental> getAllBreach();
    PageInfo<Rental> findAllRentalsByPageS(int pageNum, int pageSize);
    PageInfo<Rental> findAllBreachByPageS(int pageNum, int pageSize);
    User getUserByBookID(String bookID);
}
