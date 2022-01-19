package com.example.mvvmpractice1news.model.local.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    companion object {
        val BaseUrl = "http://newsapi.org/"
        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}