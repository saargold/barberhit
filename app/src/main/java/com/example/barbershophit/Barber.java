package com.example.barbershophit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Barber implements Serializable {
    private String email;
    private String password;
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String img;
    public static List<Service> serviceList = new ArrayList<>();

public Barber(){

}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Barber(String id,String email, String password, String firstName, String lastName, String address, String phone, String img) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.img = img;
    }

    public Barber(String email, String password, String firstName, String lastName, String address, String phone, String img) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public static void addServiceToUser(Service serviceId) {
        serviceList.add(serviceId);
    }
    public static List<Service> getAllService(){
        return serviceList;
    }
}