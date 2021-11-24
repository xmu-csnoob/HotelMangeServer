package com.example.bookManageSystem.Bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Map;

@Data
public class Rental {
    String rentalID;
    String userID;
    String bookID;
    String lentDate;
    String dueDate;
    String returnDate;
    double overDueFine;
    public Rental(){}
    public Rental(String json){
        Map maps=(Map) JSON.parse(json);
        if(maps.get("userID")!=null)
            this.userID=(String)maps.get("userID");
        if(maps.get("bookID")!=null)
            this.bookID=(String)maps.get("bookID");
        if(maps.get("lentDate")!=null)
            this.lentDate=(String)maps.get("lentDate");
        if(maps.get("dueDate")!=null)
            this.dueDate=(String)maps.get("dueDate");
        if(maps.get("returnDate")!=null)
            this.returnDate=(String)maps.get("returnDate");
        if(maps.get("overDueFine")!=null)
            this.overDueFine=Double.parseDouble((String)maps.get("overDueFile"));
    }
}
