package com.example.bbteamgo;

public class BookingManagementData {
    private int bookingOrder;
    private String bookerName;
    private String elapsedTime;

    public BookingManagementData(int order, String name, String time)
    {
        this.bookingOrder = order;
        this.bookerName = name;
        this.elapsedTime = time;
    }

    public int getBookingOrder(){
        return bookingOrder;
    }

    public String getBookerName(){
        return bookerName;
    }

    public String getElapsedTime(){
        return elapsedTime;
    }

    public void setBookingOrder(int bookingOrder){
        this.bookingOrder = bookingOrder;
    }

    public void setBookerName(String bookerName){
        this.bookerName = bookerName;
    }

    public void setElapsedTime(String elapsedTime){
        this.elapsedTime = elapsedTime;
    }
}
// elapsed time의 타입 형태는 나중에 타이머를 넣을 때 바꾸고 일단 스트링으로 썼음
// booking management page 액티비티에 있는 리사이클러뷰의 리스트 하나에 포함되어야 하는 데이터의 종류들