package com.example.barbershophit;

public class User {
    private String email;
    private String password;
    private String id;
    private String firstName;
    private String lastName;



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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User(String email, String password, String id, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}