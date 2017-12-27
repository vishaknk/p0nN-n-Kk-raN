package com.exiox.ponnanikkaran.network;

import com.exiox.ponnanikkaran.model.SignUpModel;
import com.exiox.ponnanikkaran.model.SignUpResultModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("addUser")
    Call<SignUpResultModel> signUp(@Body SignUpModel data);
}