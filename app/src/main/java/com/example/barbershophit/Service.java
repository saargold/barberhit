package com.example.barbershophit;

import java.io.Serializable;
import java.sql.Time;

public class Service implements Serializable {
    private String id;
    private String title;
    private int price;
    private Time time;
    private boolean place =false;

    public  Service(){

    }
    public Service(String id, String title, int price, boolean place) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Service(String title, int price, Time time, boolean place) {
        this.title = title;
        this.price = price;
        this.time = time;
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isPlace() {
        return place;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }
}
