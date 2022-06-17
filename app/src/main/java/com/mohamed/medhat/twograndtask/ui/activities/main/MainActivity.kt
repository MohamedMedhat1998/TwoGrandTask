package com.mohamed.medhat.twograndtask.ui.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamed.medhat.twograndtask.ui.activities.album.AlbumActivity
import com.mohamed.medhat.twograndtask.ui.compose.AlbumDetails
import com.mohamed.medhat.twograndtask.ui.compose.Title
import com.mohamed.medhat.twograndtask.ui.compose.UserDetails
import com.mohamed.medhat.twograndtask.ui.theme.TwoGrandTaskTheme
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwoGrandTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainBody()
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MainBody(viewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current
    val user by viewModel.user.observeAsState()
    val albums by viewModel.albums.observeAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Title(text = "Profile")
        user?.let { UserDetails(it) }
        Title(text = "Albums")
        albums?.let {
            LazyColumn {
                items(it) {
                    AlbumDetails(album = it) {
                        context.startActivity(Intent(context, AlbumActivity::class.java))
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TwoGrandTaskTheme {
        MainBody()
    }
}