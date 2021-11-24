package com.example.bookManageSystem.Bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Map;

@Data
public class Book {
    private String bookID;
    private String bookName;
    private String author;
    private String publisher;
    private String isbn;
    private double price;
    private String introduction;
    private int keepDays;
    private String ifLent;
    private String userID;
    private String userName;
    private String lentDate;
    private String dueDate;
    private String returnDate;
    private String image;
    private double finePerDay;
    private double overDueFine;
    private double leastPrice;
    private double largestPrice;
    public Book(){}
    public Book(String json){
        Map maps=(Map) JSON.parse(json);
        if(maps.get("bookID")!=null)
            this.bookID=(String)maps.get("bookID");
        if(maps.get("bookName")!=null)
            this.bookName=(String)maps.get("bookName");
        if(maps.get("author")!=null)
            this.author=(String)maps.get("author");
        if(maps.get("publisher")!=null)
            this.publisher=(String)maps.get("publisher");
        if(maps.get("isbn")!=null)
            this.isbn=(String)maps.get("isbn");
        if(maps.get("price")!=null)
            this.price=Double.parseDouble((String)maps.get("price"));
        if(maps.get("introduction")!=null)
            this.introduction=(String)maps.get("introduction");
        if(maps.get("keepDays")!=null)
            this.keepDays=Integer.parseInt((String)maps.get("keepDays"));
        if(maps.get("ifLent")!=null)
        {
            if(maps.get("ifLent").equals("已借出"))
                this.ifLent="1";
            else
                this.ifLent=null;
        }
            this.ifLent=(String)maps.get("ifLent");
        if(maps.get("userID")!=null)
            this.userID=(String)maps.get("userID");
        if(maps.get("userName")!=null)
            this.userName=(String)maps.get("userName");
        if(maps.get("lentDate")!=null)
            this.lentDate=(String)maps.get("lentDate");
        if(maps.get("dueDate")!=null)
            this.dueDate=(String)maps.get("dueDate");
        if(maps.get("finePerDay")!=null)
            this.finePerDay=Double.parseDouble((String)maps.get("finePerDay"));
        if(maps.get("overDueFine")!=null)
            this.overDueFine=Double.parseDouble((String)maps.get("overDueFine"));
        if(maps.get("leastPrice")!=null&&maps.get("leastPrice").toString().length()!=0)
            this.leastPrice=Double.parseDouble((String)maps.get("leastPrice"));
        if(maps.get("largestPrice")!=null&&maps.get("largestPrice").toString().length()!=0)
            this.largestPrice=Double.parseDouble((String)maps.get("largestPrice"));
    }
}
