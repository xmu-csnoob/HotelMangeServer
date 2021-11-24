package com.example.bookManageSystem.Tools;

import com.example.bookManageSystem.Bean.User;

import java.security.SecureRandom;

public class Format {
    public String checkPasswordFormat(String password){
        if(!password.matches(".*[ \\t\\n\\x0B\\f\\r]+.*")) {
            if(password.length()>=8 && password.length()<=12) {
                if(password.matches(".*[a-z]+.*") && password.matches(".*[A-Z]+.*") && password.matches(".*[0-9]+.*")) {
                    if(password.matches("[\\x00-\\xff]+")) {
                        return "格式正确";
                    }
                    else
                        return "密码中符号只能为半角符号";
                }
                else
                    return "密码中必须存在大小写字母和数字";
            }
            else
                return "密码长度必须为8到12位";
        }
        else
           return "不能存在空字符";
    }
    public String checkEmailFormat(String email){
        if(!email.matches(".*[ \\t\\n\\x0B\\f\\r]+.*")) {
            if(email.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$"))
            {
                return "格式正确";
            }
            else
                return "格式错误";
        }
        else
            return "不能存在空字符";
    }
    public String checkTelFormat(String tel){
        if(!tel.matches(".*[ \\t\\n\\x0B\\f\\r]+.*")) {
            if(!tel.matches(".*[^0-9]+.*")) {
                if(tel.length()==11) {
                    return "格式正确";
                }
                else
                    return "电话号码只能为11位";
            }
            else
                return "电话号码只能存在数字";
        }
        else
            return "不能存在空字符";
    }
    public String checkNameFormat(String name){
        if(!name.matches(".*[ \\t\\n\\x0B\\f\\r]+.*")) {
            if(name.substring(0, 1).matches("[a-zA-Z]")) {
                if(name.length()>=4&&name.length()<=12) {
                    if(name.matches("\\w+")) {
                        return "格式正确";
                    }
                    else
                        return "用户名只能由字母数字和下划线组成";
                }
                else
                    return "用户名长度必须为4到12位";
            }
            else
                return "第一位只能是字母";
        }
        else
            return "不能存在空字符";
    }
    public String generateID(){
        SecureRandom random = new SecureRandom();
        long num= 100000L * 100000;
        long id=Math.abs(random.nextLong()%num);
        return String.format("%10d",id);
    }
    public String checkUserFormat(User user){
        String msg1=checkNameFormat(user.getUserName());
        if(!msg1.equals("格式正确"))
            return msg1;
        String msg2=checkEmailFormat(user.getEmail());
        if(!msg2.equals("格式正确"))
            return msg2;
        String msg3=checkTelFormat(user.getTel());
        if(!msg3.equals("格式正确"))
            return msg3;
        String msg4=checkPasswordFormat(user.getPassword());
        if(!msg4.equals("格式正确"))
            return msg4;
        return "格式正确";
    }
}
