package com.example.thecatdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thecatdemo.data.source.DataSource

@Database(entities = [DataSource::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}
