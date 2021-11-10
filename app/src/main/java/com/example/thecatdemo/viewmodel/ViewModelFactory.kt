package com.example.thecatdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thecatdemo.data.Repository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val repository: Repository
    ): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModel(repository) as T
    }
}
