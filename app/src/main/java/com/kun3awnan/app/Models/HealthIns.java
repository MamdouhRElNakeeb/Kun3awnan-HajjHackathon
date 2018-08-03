package com.kun3awnan.app.Models;

public class HealthIns extends Service {
    public String category;
    public String HID;

    public HealthIns(String category, String HID) {
        this.category = category;
        this.HID = HID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHID() {
        return HID;
    }

    public void setHID(String HID) {
        this.HID = HID;
    }
}
