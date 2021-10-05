package com.example.thecatdemo.ui.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.thecatdemo.data.source.DataSource
import com.example.thecatdemo.viewmodel.ViewModel

@Composable
fun WebScreen(viewModel: ViewModel,
              onClickItem: (String) -> Unit = {}
) {
    val item: DataSource? = viewModel.clickDataSource
    if (item != null) {
        item.wikipedia_url?.let { onClickItem(it) }
        item.wikipedia_url?.let { OpenWeb(it) }
    }
}

@Composable
private fun OpenWeb(url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    })
}