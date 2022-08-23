package com.example.testapp.Apis;

import com.example.testapp.ModelsandAdapters.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL ="https://reqres.in/api/" ;
    @GET("users?page=1")
    Call<DataModel> getList();

}
