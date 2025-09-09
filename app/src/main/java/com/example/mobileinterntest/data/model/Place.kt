package com.example.mobileinterntest.data.model

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("display_name") val displayName: String,
    val lat: String,
    val lon: String
)
