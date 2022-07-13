package com.example.retrofitassignment.network;

import com.example.retrofitassignment.model.MarsProperty;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPIService {
    String BASE_URL = "https://mars.udacity.com/";
    @GET("realestate")
    Call<List<MarsProperty>> getProperties();

    @GET("realestate")
    Call<List<MarsProperty>> getProperties(@Query("filter") String key);
}
