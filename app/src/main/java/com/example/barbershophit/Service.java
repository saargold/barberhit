package com.example.barbershophit;

import java.io.Serializable;
import java.sql.Time;

public class Service implements Serializable  {
    private String uId;
    private String name;
    private String phone;
    private String address;
    private String title;
    private int price;
    private String status;
    private String barberId;
    private String userId;



    public  Service(){

    }



    public void setStatus(String status) {
        this.status = status;
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

    public String getBarberId() {
        return barberId;
    }

    public void setBarberId(String barberId) {
        this.barberId = barberId;
    }

    public Service(String uId, String name, String phone, String address, String title, int price, String status, String barberId) {
        this.uId = uId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.title = title;
        this.price = price;
        this.status = status;
        this.barberId = barberId;
    }

    public Service(String uId, String title, int price, String status) {
        this.uId = uId;
        this.title = title;
        this.price = price;
        this.status = status;
    }

    public Service(String uId, String title, int price, String status,String barberId,String userId) {
        this.uId = uId;
        this.title = title;
        this.price = price;
        this.status = status;
        this.barberId=barberId;
        this.userId=userId;
    }
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
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

    public String getStatus() {
        return status;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Service{" +
                "uId='" + uId + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +

                '}';
    }
}
