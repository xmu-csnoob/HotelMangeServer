package com.example.bookManageSystem.Bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Map;

@Data
public class Isbn {
    private String isbn;
    private String bookName;
    private String author;
    private String publisher;
    private double price;
    private String introduction;
    private String image;
    public Isbn(){}
    public Isbn(String json){
        Map maps=(Map) JSON.parse(json);
        if(maps.get("isbn")!=null)
            this.isbn=(String)maps.get("isbn");
        if(maps.get("bookName")!=null)
            this.bookName=(String)maps.get("bookName");
        if(maps.get("author")!=null)
            this.author=(String)maps.get("author");
        if(maps.get("publisher")!=null)
            this.publisher=(String)maps.get("publisher");
        if(maps.get("price")!=null)
            this.price=Double.parseDouble((String)maps.get("price"));
        if(maps.get("introduction")!=null)
            this.introduction=(String)maps.get("introduction");
        if(maps.get("image")!=null)
            this.image=(String)maps.get("image");
    }
}
