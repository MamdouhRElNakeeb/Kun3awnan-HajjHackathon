package com.kun3awnan.app.Models;

public class User {
    public static final User ourInstance = new User();

    public static User getInstance() {
        return ourInstance;
    }

    public User() {

    }

    public String name;
    public String password;
    public String email;
    public String phone;
    public int points;
    public String NID;
    public String UID;
}
