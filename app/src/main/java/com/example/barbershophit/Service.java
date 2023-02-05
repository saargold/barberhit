package com.example.barbershophit;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class Service implements Serializable  {
    private String id;
    private String name;
    private String phone;
    private String address;
    private String title;
    private int price;
    private boolean place =false;

    public  Service(){

    }

    public Service(String id, String name, String phone, String address, String title, int price, boolean place) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.title = title;
        this.price = price;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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



    public boolean isPlace() {
        return place;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }
//    @Exclude
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("id", id);
//        result.put("name", name);
//        result.put("phone", phone);
//        result.put("address", address);
//        result.put("title", title);
//        result.put("price", price);
//        result.put("place", place);
//
//        return result;
//    }
}
