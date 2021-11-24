package com.example.bookManageSystem.Controller;

import com.example.bookManageSystem.Bean.Rental;
import com.example.bookManageSystem.Service.RentalService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rental")
public class RentalController {
    @Autowired
    RentalService rentalService;
    //提供信息
    @RequestMapping("/rentalNum")
    public int getRentalNum(){
        return rentalService.getRentalNum();
    }
    @RequestMapping("/rentals")
    public List<Rental> getAllRental(){
        return rentalService.selectAll();
    }
    @RequestMapping("/breach")
    public List<Rental> getAllBreach(){
        return rentalService.getAllBreach();
    }
    @GetMapping("/getRentalPage")
    public PageInfo<Rental> getRentalPage(@RequestParam int pageNum){
        return rentalService.findAllRentalsByPageS(pageNum, 10);
    }
    @GetMapping("/getBreachPage")
    public PageInfo<Rental> getBreachPage(@RequestParam int pageNum){
        return rentalService.findAllBreachByPageS(pageNum, 10);
    }
}
