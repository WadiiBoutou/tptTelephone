package com.example.contacte;



public class UserModel {


private Long Id;
    private String number;





    private String imei;

    public UserModel(String number, String imei) {

        this.number = number;

        this.imei = imei;
    }



    public String getNumber() {
        return number;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getImei() {
        return imei;
    }



    public void setNumber(String number) {
        this.number = number;
    }



    public void setImei(String imei) {
        this.imei = imei;
    }


}