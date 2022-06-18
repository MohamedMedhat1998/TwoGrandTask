package com.mohamed.medhat.twograndtask.ui.activities.album

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamed.medhat.twograndtask.ui.activities.main.ALBUM_ID
import com.mohamed.medhat.twograndtask.ui.compose.PhotoItem
import com.mohamed.medhat.twograndtask.ui.compose.SearchTextField
import com.mohamed.medhat.twograndtask.ui.theme.TwoGrandTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class AlbumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: AlbumViewModel = viewModel()
            if (intent != null && intent.hasExtra(ALBUM_ID)) {
                viewModel.loadPhotos(intent.extras?.getInt(ALBUM_ID) ?: -1)
            }
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
fun AlbumsBody(viewModel: AlbumViewModel = viewModel()) {
    val searchValue by viewModel.filter.observeAsState()
    val photos by viewModel.photos.observeAsState()
    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.heightIn(min = 48.dp)) {
            Surface(elevation = 4.dp) {
                SearchTextField(value = searchValue ?: "", onValueChanged = { viewModel.setFilter(it) })
            }
        }
        photos?.let {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(1.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(it) {
                    PhotoItem(photo = it)
                }
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