package com.example.thecatdemo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.thecatdemo.R
import com.example.thecatdemo.data.source.Image
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun DialogDeleteScreen(viewModel: ViewModel,
                       onDismiss: () -> Unit = {},
                       onNegativeClick: () -> Unit = {},
                       onPositiveClick: () -> Unit = {}
) {
    viewModel.clickDataSource?.image?.let {
        DialogShow(
        onDismiss = onDismiss,
        onNegativeClick = onNegativeClick,
        onPositiveClick = onPositiveClick,
        image = it
    )
    }
}

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
private fun DialogShow(onDismiss: () -> Unit = {},
                       onNegativeClick: () -> Unit = {},
                       onPositiveClick: () -> Unit = {},
                       image: Image
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DialogImage(image)
                TextDelete()
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 40.dp, 0.dp),
                        onClick = onNegativeClick
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                    TextButton(
                        modifier = Modifier
                            .padding(40.dp, 0.dp, 0.dp, 0.dp),
                        onClick = { onPositiveClick() }
                    ) {
                        Text(text = stringResource(id = R.string.ok))
                    }
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun DialogImage(image: Image) {
    Image(
        rememberImagePainter(data = image.url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(300.dp)
            .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
    )
}

@Composable
private fun TextDelete() {
    Text(
        text = stringResource(id = R.string.delete),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(8.dp)
    )
}