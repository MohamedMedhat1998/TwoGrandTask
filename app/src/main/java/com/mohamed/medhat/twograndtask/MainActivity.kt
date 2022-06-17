package com.mohamed.medhat.twograndtask

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mohamed.medhat.twograndtask.ui.compose.*
import com.mohamed.medhat.twograndtask.ui.theme.TwoGrandTaskTheme

private const val TAG = "MainActivity"

@ExperimentalFoundationApi
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
fun MainBody() {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Title(text = "Profile")
        // TODO update the fakeUser with real data
        UserDetails(user = fakeUser)
        Title(text = "Albums")
        // TODO update the fakeAlbums with real data
        val fakeAlbums = List(30) { fakeAlbum }
        LazyColumn {
            items(fakeAlbums) {
                AlbumDetails(album = it) {
                    context.startActivity(Intent(context, AlbumActivity::class.java))
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