package com.example.thecatdemo.data.remote

import android.util.Log
import com.example.thecatdemo.data.source.DataSource

class RemoteModel (private val service: ApiService) {

    suspend fun getData(): MutableList<DataSource> {
        return try {
           service.getData()
        } catch (e: Exception) {
            Log.d("!!!", e.toString())
            mutableListOf()
        }
    }
}