package com.example.mobileinterntest.data.repository

import com.example.mobileinterntest.data.remote.RetrofitInstance

class SearchRepository {
        suspend fun searchPlaces(apiKey: String, query:String) =
            RetrofitInstance.api.searchPlaces(apiKey, query)


}