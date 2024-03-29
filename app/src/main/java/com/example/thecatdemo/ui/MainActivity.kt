package com.example.thecatdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.ViewModelProvider
import coil.annotation.ExperimentalCoilApi
import com.example.thecatdemo.ui.theme.TheCatDemoTheme
import com.example.thecatdemo.viewmodel.ViewModel
import com.example.thecatdemo.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = ViewModelProvider(this, factory)[ViewModel::class.java]
            viewModel.getData()

            TheCatDemoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppScreen(viewModel = viewModel)
                }
            }
        }
    }
}