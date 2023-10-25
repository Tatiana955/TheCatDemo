package com.example.thecatdemo.data.remote

import com.example.thecatdemo.data.source.DataSource
import retrofit2.http.GET

// Warning: This api no longer contains images
const val BASE_URL = "https://api.thecatapi.com/v1/"

interface ApiService {
    @GET("breeds")
    suspend fun getData() : MutableList<DataSource>
}