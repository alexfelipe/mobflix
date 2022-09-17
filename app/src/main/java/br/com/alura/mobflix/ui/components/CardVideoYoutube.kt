package br.com.alura.mobflix.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.mobflix.R
import br.com.alura.mobflix.model.Category
import br.com.alura.mobflix.model.YoutubeVideo
import br.com.alura.mobflix.ui.screens.Tag
import br.com.alura.mobflix.ui.theme.MobflixTheme
import coil.compose.AsyncImage

@Composable
fun CardVideoYoutube(
    video: YoutubeVideo,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
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
}

@Preview
@Composable
fun CardVideoYoutubePreview() {
    MobflixTheme {
        Surface(color = MaterialTheme.colors.background) {
            CardVideoYoutube(
                video = YoutubeVideo(
                    category = Category.MOBILE
                )
            )
        }
    }
}