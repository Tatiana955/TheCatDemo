package com.example.thecatdemo.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecatdemo.data.Repository
import com.example.thecatdemo.data.source.DataSource
import com.example.thecatdemo.data.source.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel (private val repository: Repository): ViewModel(),
    LifecycleObserver {

    private val scope = CoroutineScope(Dispatchers.IO)

    val dataSource: MutableLiveData<MutableList<DataSource>> by lazy {
        MutableLiveData<MutableList<DataSource>>()
    }

    val dataSourceLocal: MutableLiveData<MutableList<DataSource>> by lazy {
        MutableLiveData<MutableList<DataSource>>()
    }

    var clickDataSource: DataSource? = null
    var zoomImage: Image? = null

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
            dataSourceLocal.value?.add(dataSource)
        }
    }

    fun deleteDataSource(dataSource: DataSource) {
        scope.launch {
            repository.deleteDataSource(dataSource)
        }
    }

    fun deleteByPrimaryKey(id: Int) {
        scope.launch {
            repository.deleteByPrimaryKey(id)
        }
    }

    fun clearDataSource() {
        scope.launch {
            repository.clearDataSource()
        }
    }
}