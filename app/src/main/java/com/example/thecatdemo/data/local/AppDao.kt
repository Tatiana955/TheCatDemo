package com.example.thecatdemo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.thecatdemo.data.source.DataSource

@Dao
interface AppDao {

    @Query("SELECT * FROM data")
    suspend fun getDataSource(): MutableList<DataSource>

    @Insert
    suspend fun insertDataSource(dataSource: DataSource)

    @Delete
    suspend fun deleteDataSource(dataSource: DataSource)

    @Query("DELETE FROM data")
    suspend fun clearDataSource()
}