package com.example.thecatdemo.data

import com.example.thecatdemo.data.remote.RemoteModel
import com.example.thecatdemo.data.source.DataSource
import javax.inject.Inject

class Repository @Inject constructor(private val remoteModel: RemoteModel) {

    suspend fun getData(): MutableList<DataSource> {
        return remoteModel.getData()
    }
}