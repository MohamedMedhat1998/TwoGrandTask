package com.mohamed.medhat.twograndtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamed.medhat.twograndtask.ui.compose.PhotoItem
import com.mohamed.medhat.twograndtask.ui.compose.SearchTextField
import com.mohamed.medhat.twograndtask.ui.compose.fakePhoto
import com.mohamed.medhat.twograndtask.ui.theme.TwoGrandTaskTheme

@ExperimentalFoundationApi
class AlbumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwoGrandTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AlbumsBody()
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun AlbumsBody() {
    var searchValue by remember {
        mutableStateOf("")
    }
    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.heightIn(min = 48.dp)) {
            Surface(elevation = 4.dp) {
                SearchTextField(value = searchValue, onValueChanged = { searchValue = it })
            }
        }
        // TODO replace the fake photos with real data
        val fakePhotos = List(20) { fakePhoto }
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            contentPadding = PaddingValues(1.dp)
        ) {
            items(fakePhotos) {
                PhotoItem(photo = it)
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TwoGrandTaskTheme {
        AlbumsBody()
    }
}