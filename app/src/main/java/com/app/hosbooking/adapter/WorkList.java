package com.app.hosbooking.adapter;

public class WorkList {

    public String img;
    public String mid;
    public String title;
    public String desc;
    public String state;
    public String type;
    public String price;

    public WorkList() { }

    public WorkList(String img, String mid, String title, String desc, String state, String type, String price){
        this.img = img;
        this.mid = mid;
        this.title = title;
        this.desc = desc;
        this.state = state;
        this.type = type;
        this.price = price;
    }
}