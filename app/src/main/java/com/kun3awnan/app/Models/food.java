package com.kun3awnan.app.Models;

public class food {
    private String food_head;
    private String food_decription;

    // constructor
    public food(String food_head, String food_decription) {
        this.food_head = food_head;
        this.food_decription = food_decription;
    }

    // setters

    public void setFood_head(String food_head) {
        this.food_head = food_head;
    }

    public void setFood_decription(String food_decription) {
        this.food_decription = food_decription;
    }

    // getters

    public String getFood_head() {
        return food_head;
    }

    public String getFood_decription() {
        return food_decription;
    }
}
