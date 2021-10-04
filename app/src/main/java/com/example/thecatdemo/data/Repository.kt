package com.example.thecatdemo.data

import com.example.thecatdemo.data.local.LocalModel
import com.example.thecatdemo.data.remote.RemoteModel
import com.example.thecatdemo.data.source.DataSource
import javax.inject.Inject

class Repository @Inject constructor(private val remoteModel: RemoteModel,
                                     private val localModel: LocalModel) {

    suspend fun getData(): MutableList<DataSource> {
        return remoteModel.getData()
    }

    suspend fun getDataSource(): MutableList<DataSource> {
        return localModel.getDataSource()
    }

    suspend fun insertDataSource(dataSource: DataSource) {
        localModel.insertDataSource(dataSource)
    }

    suspend fun deleteDataSource(dataSource: DataSource) {
        localModel.deleteDataSource(dataSource)
    }

    suspend fun clearDataSource() {
        localModel.clearDataSource()
    }
}