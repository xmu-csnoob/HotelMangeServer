package cn.edu.xmu.wwf.privilege.utils;

public enum ReturnNo {
    OK(0),
    FORBIDDEN(701),
    RES0URCE_NOT_FOUND(702),
    RESOURCE_OUT_OF_RANGE(703),
    DATA_FORMAT_ERR0R(801);
    int code;
    ReturnNo(int code){
        this.code=code;
    }
}
