package br.com.alura.mobflix.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.lifecycle.lifecycleScope
import br.com.alura.mobflix.model.Category
import br.com.alura.mobflix.model.YoutubeVideo
import br.com.alura.mobflix.sampleData.saveVideo
import br.com.alura.mobflix.ui.screens.CardVideoYoutube
import br.com.alura.mobflix.ui.theme.MobflixTheme
import kotlinx.coroutines.launch

class FormVideoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobflixTheme {
                Surface(color = MaterialTheme.colors.background) {
                    FormVideoApp(onRegisterClick = { video ->
                        lifecycleScope.launch {
                            saveVideo(video)
                            finish()
                        }
                    })
                }
            }
        }
    }

}

@Composable
fun FormVideoApp(onRegisterClick: (YoutubeVideo) -> Unit = {}) {
    var youtubeId by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val selectedCategory: Category? = remember(category) {
        try {
            Category.valueOf(category)
        } catch (e: Exception) {
            null
        }
    }
    val video = remember(youtubeId, selectedCategory) {
        YoutubeVideo(youtubeId, selectedCategory)
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
                text = "Cadastre um vídeo",
                Modifier
                    .fillMaxWidth(),
                fontSize = 32.sp
            )
        }
        item {
            FormTextField(
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
            FormTextField(
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
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            focusManager.clearFocus(true)
                            onRegisterClick(video)
                        },
                    color = Color(0xFF2478DF),
                    elevation = 4.dp
                ) {
                    Text(
                        text = "Cadastrar",
                        Modifier.padding(10.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
//                Surface(
//                    Modifier
//                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(8.dp))
//                        .clickable { },
//                    color = Color(0xFF2478DF),
//                    elevation = 4.dp
//                ) {
//                    Text(
//                        text = "Alterar",
//                        Modifier.padding(10.dp),
//                        color = Color.White,
//                        textAlign = TextAlign.Center
//                    )
//                }
//                Surface(
//                    Modifier
//                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(8.dp))
//                        .clickable { },
//                    color = Color(0xFFD82D2D),
//                    elevation = 4.dp
//                ) {
//                    Text(
//                        text = "Remover",
//                        Modifier.padding(10.dp),
//                        color = Color.White,
//                        textAlign = TextAlign.Center
//                    )
//                }
            }
        }
    }
}

@Composable
private fun FormTextField(
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
            focusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(placeholder)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FormVideoAppPreview() {
    MobflixTheme {
        Surface(color = MaterialTheme.colors.background) {
            FormVideoApp()
        }
    }
}