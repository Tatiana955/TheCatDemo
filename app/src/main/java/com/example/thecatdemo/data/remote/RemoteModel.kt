package com.example.thecatdemo.data.remote

import android.util.Log
import com.example.thecatdemo.data.source.DataSource
import javax.inject.Inject

class RemoteModel @Inject constructor() {
    private val service = ApiService.create()

    suspend fun getData(): MutableList<DataSource> {
        return try {
            val data = service.getData()
            val list = mutableListOf<DataSource>()
            list.addAll(data)
            list
        } catch (e: Exception) {
            Log.d("!!!", e.toString())
            mutableListOf()
        }
    }
}