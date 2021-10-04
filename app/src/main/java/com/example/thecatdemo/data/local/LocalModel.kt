package com.example.thecatdemo.data.local

import android.content.Context
import androidx.room.Room
import com.example.thecatdemo.data.source.DataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "data"
    ).build()

    suspend fun getDataSource(): MutableList<DataSource> {
        return database.appDao().getDataSource()
    }

    suspend fun insertDataSource(dataSource: DataSource) {
        database.appDao().insertDataSource(dataSource)
    }

    suspend fun deleteDataSource(dataSource: DataSource) {
        database.appDao().deleteDataSource(dataSource)
    }

    suspend fun clearDataSource() {
        database.appDao().clearDataSource()
    }
}
