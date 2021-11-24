package com.example.bookManageSystem.Tools;

import java.util.Calendar;
import java.util.Date;

public class Time {
    public Time(){

    }
    public int dateDiffForDay(String date1,String date2){
        String[] dateInfo1=date1.split("-");
        String[] dateInfo2=date2.split("-");
        int year=Integer.parseInt(dateInfo1[0])-Integer.parseInt(dateInfo2[0]);
        int month=Integer.parseInt(dateInfo1[1])-Integer.parseInt(dateInfo2[1]);
        int day=Integer.parseInt(dateInfo1[2])-Integer.parseInt(dateInfo2[2]);
        return year*12*30+month*30+day;
    }
    public String dateAddDay(String date,int days){
        String[] dateInfo=date.split("-");
        int year=Integer.parseInt(dateInfo[0]);
        int month=Integer.parseInt(dateInfo[1]);
        int day=Integer.parseInt(dateInfo[2]);
        day+=days;
        while(day>30) {
            day-=30;
            month++;
        }
        while(month>12) {
            month-=12;
            year++;
        }
        return year+"-"+month+"-"+day;
    }
    public String getCurrentDate(){
        Calendar calendar=Calendar.getInstance();
        String time=calendar.getTime().toString();
        System.out.println(time);
        return time;
    }
}
