package br.com.alura.mobflix.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.mobflix.R
import br.com.alura.mobflix.model.Category
import br.com.alura.mobflix.model.YoutubeVideo
import br.com.alura.mobflix.sampleData.sampleVideos
import br.com.alura.mobflix.ui.theme.MobflixTheme
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxSize()
    ) {
        Box(
            Modifier
                .height(140.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                "https://i.ytimg.com/vi/2t8ycK8D4Rk/maxresdefault.jpg",
                contentDescription = null,
                Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(
                    id = R.drawable.preview_image_placeholder
                )
            )
            Surface(
                Modifier
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.BottomCenter)
                    .clickable { },
                color = Color(0xFF2478DF),
                elevation = 4.dp
            ) {
                Text(
                    text = "Assista agora",
                    Modifier.padding(10.dp),
                    color = Color.White,
                )
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 28.dp,
            )
        ) {
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(
                        start = 36.dp,
                        bottom = 16.dp,
                        end = 36.dp,
                    )
                ) {
                    items(Category.values()) { category ->
                        Tag(category)
                    }
                }
            }
            items(sampleVideos) { video ->
                Column(
                    Modifier.padding(
                        top = 18.dp,
                        start = 36.dp,
                        end = 36.dp,
                    )
                ) {
                    CardVideoYoutube(video)
                }
            }
        }

    }
}

@Composable
fun CardVideoYoutube(
    video: YoutubeVideo
) {
    video.category?.let {
        Tag(
            category = it,
            Modifier.padding(bottom = 8.dp),
        )
    }
    AsyncImage(
        video.thumb,
        contentDescription = null,
        Modifier
            .height(180.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.preview_video_placeholder),
        error = painterResource(id = R.drawable.preview_video_placeholder)
    )
}

@Composable
fun Tag(category: Category, modifier: Modifier = Modifier) {
    Box(
        modifier
            .background(
                color = Color(category.backgroundColor),
                shape = RoundedCornerShape(12.dp),
            )
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
    ) {
        Text(
            text = category.id,
            style = LocalTextStyle.current.copy(
                color = Color(category.textColor)
            ),
        )
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MobflixTheme {
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            HomeScreen()
        }
    }
}