package com.example.bookManageSystem.Tools;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryptor {
    public Encryptor(){

    }
    public String encode(String original_code){
        byte[] bytes=original_code.getBytes(StandardCharsets.UTF_8);
        byte[] encode=Base64.getEncoder().encode(bytes);
        String final_code=new String(encode);
        return final_code;
    }
}
