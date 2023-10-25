package com.example.thecatdemo.di

import android.content.Context
import androidx.room.Room
import com.example.thecatdemo.data.Repository
import com.example.thecatdemo.data.local.AppDatabase
import com.example.thecatdemo.data.local.LocalModel
import com.example.thecatdemo.data.remote.ApiService
import com.example.thecatdemo.data.remote.BASE_URL
import com.example.thecatdemo.data.remote.RemoteModel
import com.example.thecatdemo.viewmodel.ViewModel
import com.example.thecatdemo.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        var appDatabase: AppDatabase? = null
        return appDatabase ?: synchronized(this) {
            val database: AppDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "data"
            ).build()
            appDatabase = database
            database
        }
    }

    @Singleton
    @Provides
    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemote(
        service: ApiService
    ): RemoteModel {
        return RemoteModel(service)
    }

    @Singleton
    @Provides
    fun provideLocal(
        database: AppDatabase
    ): LocalModel {
        return LocalModel(database.appDao())
    }

    @Singleton
    @Provides
    fun provideRepository(
        remote: RemoteModel,
        local: LocalModel
    ): Repository {
        return Repository(remote, local)
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(
        repository: Repository
    ): ViewModelFactory {
        return ViewModelFactory(repository)
    }

    @Singleton
    @Provides
    fun provideViewModel(
        repository: Repository
    ): ViewModel {
        return ViewModel(repository)
    }
}
