package com.example.mvvmpractice1news.model.local.remote.api

import com.example.mvvmpractice1news.model.local.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  ApiService{

    @GET("v2/everything")
    suspend fun getData(
        @Query("q") q:String,
        @Query("apikey") apiKey:String):Response<ResponseDTO>

}