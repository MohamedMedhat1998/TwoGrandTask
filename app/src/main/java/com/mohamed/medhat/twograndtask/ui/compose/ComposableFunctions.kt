package com.mohamed.medhat.twograndtask.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mohamed.medhat.twograndtask.R
import com.mohamed.medhat.twograndtask.model.*
import com.mohamed.medhat.twograndtask.ui.theme.Shapes

@Composable
fun PhotoItem(photo: Photo) {
    AsyncImage(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth,
        model = ImageRequest.Builder(LocalContext.current)
            .data(photo.thumbnailUrl)
            .crossfade(true)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .build(), contentDescription = photo.title
    )
}

@Composable
fun SearchTextField(
    value: String,
    placeholder: String = "Search",
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value, onValueChange = onValueChanged, leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_search_24),
                contentDescription = "",
                tint = Color(0xFF808080)
            )
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = {
            Text(text = placeholder, color = Color(0xFF808080))
        }
    )
}

@Composable
fun AlbumDetails(album: Album, onClick: () -> Unit) {
    ContentCard(elevation = 4.dp, onClick = onClick) {
        Text(text = album.title, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun UserDetails(user: User) {
    ContentCard(elevation = 4.dp) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = user.name)
            Text(text = user.getAddress())
        }
    }
}

@Composable
fun ContentCard(elevation: Dp, onClick: () -> Unit = {}, content: @Composable () -> Unit) {
    Surface(
        shape = Shapes.medium,
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            },
        elevation = elevation,
    ) {
        content.invoke()
    }
}

@Composable
fun Title(text: String, textStyle: TextStyle = MaterialTheme.typography.h5) {
    Text(
        text = text,
        style = textStyle,
        modifier = Modifier.padding(top = 8.dp, start = 8.dp),
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun ComposableFunctionsPreview() {
    Column(Modifier.fillMaxSize()) {
        UserDetails(user = fakeUser)
        AlbumDetails(album = fakeAlbum) {}
        SearchTextField(value = "", onValueChanged = {})
        PhotoItem(photo = fakePhoto)
    }
}

val fakePhoto = Photo(
    albumId = 1,
    id = 1,
    title = "accusamus beatae ad facilis cum similique qui sunt",
    url = "https://via.placeholder.com/600/92c952",
    thumbnailUrl = "https://via.placeholder.com/150/92c952"
)
val fakeAlbum = Album(id = 1, title = "quidem molestiae enim", 1)
val fakeUser = User(
    website = "hildegard.org",
    address = Address(
        zipcode = "92998-3874",
        geo = Geo(lng = "81.1496", lat = "-37.3159"),
        suite = "Apt. 556",
        city = "Gwenborough",
        street = "Kulas Light"
    ),
    phone = "1-770-736-8031 x56442",
    name = "Leanne Graham",
    company = Company(
        bs = "harness real-time e-markets",
        catchPhrase = "Multi-layered client-server neural-net",
        name = "Romaguera-Crona"
    ),
    id = 1,
    email = "Sincere@april.biz",
    username = "Bret"
)
