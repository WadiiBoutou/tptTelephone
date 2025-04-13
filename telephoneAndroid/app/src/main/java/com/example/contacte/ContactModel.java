package com.example.contacte;

public class ContactModel {
    private String name;
    private String number;
    private Long userId;

    public ContactModel(String name,  String number,Long userId) {
        this.name = name;
        this.userId = userId;
        this.number = number;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}