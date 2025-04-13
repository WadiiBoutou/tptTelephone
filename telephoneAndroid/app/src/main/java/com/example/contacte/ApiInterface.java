package com.example.contacte;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("api/user/add")
    Call<Void> addUser(@Body UserModel user);
    @GET("api/user/check/{imei}")
    Call<Boolean> checkUserExist(@Path("imei") String imei);
    @POST("api/contact/add")
    Call<Void> addContact(@Body ContactModel contact);

    @GET("api/user/by-imei/{imei}")
    Call<Long> getUserByImei(@Path("imei") String imei);

}
