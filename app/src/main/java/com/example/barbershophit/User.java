package com.example.barbershophit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String email;
    private String password;
    private String id;
    private String firstName;
    private String lastName;
    public static List<Service> serviceList = new ArrayList<>();


    public User(){

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


public User(String email, String password, String firstName, String lastName) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
}

    public User(String id,String email, String password, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  List<Service> getServiceList() {
        return serviceList;
    }

    public void addSer(Service service){
        serviceList.add(service);

    }

    public static void addServiceToUser(Service serviceId) {
        serviceList.add(serviceId);
    }
    public static List<Service> getAllService(){

        return serviceList;
    }

}