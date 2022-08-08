package com.ntgclarity.authenticator.Network

import com.example.example.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything
//https://newsapi.org/v2/everything?q=tesla&from=2022-07-07&sortBy=publishedAt&apiKey=ddfae166ffff495489147ec8b066775d
interface NewsApi {
    @GET("v2/everything")
    fun news(@Query("q")q:String , @Query("apiKey") apikey:String): Call<News?>?
}
var retrofit: NewsApi = Retrofit.Builder()
    .baseUrl("https://newsapi.org/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(NewsApi::class.java)
