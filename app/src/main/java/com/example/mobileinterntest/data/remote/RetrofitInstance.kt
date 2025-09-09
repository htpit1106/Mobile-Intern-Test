package com.example.mobileinterntest.data.remote
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val client = OkHttpClient.Builder().build()

    val api: ApiSearch by lazy{
        Retrofit.Builder()
            .baseUrl("https://us1.locationiq.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiSearch::class.java)
    }
}
