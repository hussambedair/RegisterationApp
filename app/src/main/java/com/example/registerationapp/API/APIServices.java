package com.example.registerationapp.API;

import com.example.registerationapp.API.Models.DefaultResponse;
import com.example.registerationapp.API.Models.LoginResponse;
import com.example.registerationapp.API.Models.UsersResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIServices {

    //Here, we define all the API calls

    @FormUrlEncoded //add this annotation
    @POST("createuser")  // createuser is endpoint of our url
    public Call<DefaultResponse> createUser ( //if you dont't know the type of response, set it to ResponseBody temporarly
                                              // Here, we'll define all the fields that we need to
                                              // send while creating the user
                                              @Field("email") String email,
                                              @Field("password") String password,
                                              @Field("name") String name,
                                              @Field("school") String school);


    @FormUrlEncoded
    @POST("userlogin")  // createuser is endpoint of our url
    public Call<LoginResponse> loginUser (

                                              @Field("email") String email,
                                              @Field("password") String password);

    @GET("allusers")
    public Call<UsersResponse> getAllUsers ();






}
