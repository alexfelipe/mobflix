package br.com.alura.mobflix.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.mobflix.model.Category
import br.com.alura.mobflix.model.YoutubeVideo
import br.com.alura.mobflix.sampleData.findYoutubeVideoById
import br.com.alura.mobflix.ui.components.CardVideoYoutube
import br.com.alura.mobflix.ui.theme.MobflixTheme
import java.util.*

// TODO usar state holder
@Composable
fun FormVideoScreen(
    videoId: String? = null,
    onRegisterClick: (YoutubeVideo) -> Unit = {},
) {
    val titleScreen = videoId?.let { "Editando vídeo" } ?: "Cadastre um vídeo"
    val currentVideoId: String = videoId ?: UUID.randomUUID().toString()
    val foundVideo = findYoutubeVideoById(currentVideoId)
    var youtubeId by remember { mutableStateOf(foundVideo?.youtubeId ?: "") }
    var url by remember { mutableStateOf(youtubeId) }
    var category by remember { mutableStateOf(foundVideo?.category?.toString() ?: "") }
    val selectedCategory: Category? = remember(category) {
        try {
            Category.valueOf(category)
        } catch (e: Exception) {
            null
        }
    }
    val video = remember(youtubeId, selectedCategory) {
        YoutubeVideo(
            currentVideoId,
            youtubeId = youtubeId,
            category = selectedCategory
        )
    }
    val focusManager = LocalFocusManager.current
    LazyColumn(
        Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {
                focusManager.clearFocus(true)
            },
        contentPadding = PaddingValues(36.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        item {
            Text(
                text = titleScreen,
                Modifier
                    .fillMaxWidth(),
                fontSize = 32.sp
            )
        }
        item {
            FormVideoTextField(
                url,
                onValueChange = {
                    url = it
                },
                Modifier.onFocusChanged {
                    if (!it.isFocused) {
                        youtubeId = url
                    }
                },
                label = "URL",
                placeholder = "id do youtube",
            )
        }
        item {
            FormVideoTextField(
                category,
                onValueChange = {
                    category = it.uppercase()
                },
                Modifier,
                label = "Categoria",
                placeholder = "Mobile, FRONT-END...",
            )
        }
        item {
            if (youtubeId.isNotBlank()) {
                Column {
                    Text(
                        text = "Preview",
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontSize = 28.sp
                    )
                    CardVideoYoutube(
                        video
                    )
                }
            }
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                if (videoId == null) {
                    PositiveFormVideoButton(text = "Cadastrar",
                        Modifier.clickable {
                            focusManager.clearFocus(true)
                            onRegisterClick(video)
                        })
                } else {
                    PositiveFormVideoButton(
                        text = "Alterar",
                        Modifier.clickable {
                            focusManager.clearFocus(true)
                            onRegisterClick(video)
                        }
                    )
                    NegativeFormVideoButton("Remover")
                }

            }
        }
    }
}

@Composable
private fun NegativeFormVideoButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        color = Color(0xFFD82D2D),
        elevation = 4.dp
    ) {
        Text(
            text = text,
            Modifier.padding(10.dp),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PositiveFormVideoButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        color = Color(0xFF2478DF),
        elevation = 4.dp
    ) {
        Text(
            text = text,
            Modifier.padding(10.dp),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun FormVideoTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = ""
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier.fillMaxWidth(),
        label = {
            Text(text = (label))
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = Color(0xFF275FA3),
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color.White,
            cursorColor = Color.White,
            placeholderColor = Color(0xFFB0B0B0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(placeholder)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FormVideoScreenWithVideoId() {
    MobflixTheme {
        Surface(color = MaterialTheme.colors.background) {
            FormVideoScreen()
        }
    }
}

@Preview
@Composable
fun FormVideoScreenWithoutVideoId() {
    MobflixTheme {
        Surface(color = MaterialTheme.colors.background) {
            FormVideoScreen(UUID.randomUUID().toString())
        }
    }
}