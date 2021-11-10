package com.example.thecatdemo.data.local

import com.example.thecatdemo.data.source.DataSource

class LocalModel internal constructor(private val appDao: AppDao) {

    suspend fun getDataSource(): MutableList<DataSource> {
        return appDao.getDataSource()
    }

    suspend fun insertDataSource(dataSource: DataSource) {
        appDao.insertDataSource(dataSource)
    }

    suspend fun deleteDataSource(dataSource: DataSource) {
        appDao.deleteDataSource(dataSource)
    }

    suspend fun deleteByPrimaryKey(primaryKey: Int) {
        appDao.deleteByPrimaryKey(primaryKey)
    }

    suspend fun clearDataSource() {
        appDao.clearDataSource()
    }
}
