package cn.edu.xmu.wwf.privilege.utils;

import lombok.Data;

@Data
public class ReturnObject <T>{
    ReturnNo returnNo;
    String errMsg;
    T data;
    public ReturnObject(T data){
        returnNo=ReturnNo.OK;
        errMsg="成功";
        this.data=data;
    }
    public ReturnObject(ReturnNo returnNo,String errMsg){
        this.returnNo=returnNo;
        this.errMsg=errMsg;
        data=null;
    }
}
