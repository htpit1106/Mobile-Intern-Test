package com.example.mobileinterntest.data.remote

import com.example.mobileinterntest.data.model.Place
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSearch {
    @GET("search.php")
    suspend fun searchPlaces(
        @Query("key") apiKey:String,
        @Query("q") query: String,
        @Query("format") format: String = "json",
        @Query("limit") limit: Int = 10
    ):List<Place>

}