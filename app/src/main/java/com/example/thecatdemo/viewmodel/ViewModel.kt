package com.example.thecatdemo.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecatdemo.data.Repository
import com.example.thecatdemo.data.source.DataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository): ViewModel(),
    LifecycleObserver {

    private val scope = CoroutineScope(Dispatchers.IO)

    val dataSource: MutableLiveData<MutableList<DataSource>> by lazy {
        MutableLiveData<MutableList<DataSource>>()
    }

    val dataSourceLocal: MutableLiveData<MutableList<DataSource>> by lazy {
        MutableLiveData<MutableList<DataSource>>()
    }

    var clickDataSource: DataSource? = null

    fun getData() {
        scope.launch {
            val dataRep = repository.getData()
            dataSource.postValue(dataRep)
        }
    }

    fun getDataSource() {
        scope.launch {
            val dataRep = repository.getDataSource()
            dataSourceLocal.postValue(dataRep)
        }
    }

    fun insertDataSource(dataSource: DataSource) {
        scope.launch {
            repository.insertDataSource(dataSource)
        }
    }

    fun deleteDataSource(dataSource: DataSource) {
        scope.launch {
            repository.deleteDataSource(dataSource)
        }
    }

    fun clearDataSource() {
        scope.launch {
            repository.clearDataSource()
        }
    }
}