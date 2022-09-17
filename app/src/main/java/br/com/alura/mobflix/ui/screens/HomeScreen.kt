package br.com.alura.mobflix.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import br.com.alura.mobflix.ui.components.CardVideoYoutube
import br.com.alura.mobflix.ui.theme.MobflixTheme
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    videos: List<YoutubeVideo> = emptyList(),
    onPlayNowClick: (YoutubeVideo) -> Unit = {},
    onLongClickVideo: (YoutubeVideo) -> Unit = {},
    onClickVideo: (YoutubeVideo) -> Unit = {}
) {
    Column(
        modifier
            .fillMaxSize()
    ) {
        MainBannerVideo(
            YoutubeVideo(
                youtubeId = "94yuIVdoevc",
            ),
            onPlayNowClick = { video ->
                onPlayNowClick(video)
            }
        )
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 28.dp,
            )
        ) {
            item {
                CategoriesList(Category.values())
            }
            items(videos) { video ->
                Column(
                    Modifier.padding(
                        top = 18.dp,
                        start = 36.dp,
                        end = 36.dp,
                    )
                ) {
                    CardVideoYoutube(
                        video,
                        Modifier.combinedClickable(
                            onClick = {
                                onClickVideo(video)
                            },
                            onLongClick = {
                                onLongClickVideo(video)
                            }
                        ),
                    )
                }
            }
        }

    }
}

@Composable
private fun MainBannerVideo(
    video: YoutubeVideo,
    onPlayNowClick: (YoutubeVideo) -> Unit = {}
) {
    Box(
        Modifier
            .height(140.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            video.thumb,
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
                .clickable {
                    onPlayNowClick(video)
                },
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
}

@Composable
private fun CategoriesList(categories: Array<Category>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            start = 36.dp,
            bottom = 16.dp,
            end = 36.dp,
        )
    ) {
        items(categories) { category ->
            Tag(category)
        }
    }
}

@Composable
fun Tag(
    category: Category,
    modifier: Modifier = Modifier,
) {
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
            HomeScreen(
                videos = listOf(
                    YoutubeVideo(),
                    YoutubeVideo(
                        youtubeId = "test",
                        category = Category.MOBILE,
                    ),
                )
            )
        }
    }
}