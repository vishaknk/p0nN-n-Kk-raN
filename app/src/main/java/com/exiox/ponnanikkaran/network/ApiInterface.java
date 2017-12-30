package com.exiox.ponnanikkaran.network;

import com.exiox.ponnanikkaran.model.posts.PostResult;
import com.exiox.ponnanikkaran.model.signUp.SignUpModel;
import com.exiox.ponnanikkaran.model.signUp.SignUpResultModel;
import com.exiox.ponnanikkaran.model.Sigin.SigninResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @POST("addUser")
    Call<SignUpResultModel> signUp(@Body SignUpModel data);
    @GET("userLogin")
    Call<SigninResult> signin(@Query("loginUsername") String email, @Query("loginPassword") String password);
    @GET("listPosts")
    Call<PostResult> getpost(@Query("index") int index, @Query("count") int limit);
}