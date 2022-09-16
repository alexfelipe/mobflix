package br.com.alura.mobflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.mobflix.ui.screens.CardVideo
import br.com.alura.mobflix.ui.theme.MobflixTheme

class FormVideoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobflixTheme {
                Surface(color = MaterialTheme.colors.background) {
                    FormVideoApp()
                }
            }
        }
    }

}

@Composable
fun FormVideoApp() {
    LazyColumn(
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
            var url by remember { mutableStateOf("") }
            FormTextField(
                url,
                onValueChange = {
                    url = it
                },
                label = "URL",
                placeholder = "id do youtube",
            )
        }
        item {
            var categoria by remember { mutableStateOf("") }
            FormTextField(
                categoria,
                onValueChange = {
                    categoria = it
                },
                label = "Categoria",
                placeholder = "Mobile, Front-End...",
            )
        }

        item {
            Column {
                Text(
                    text = "Preview",
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontSize = 28.sp
                )
                CardVideo(
                    title = "mobile",
                    img = "https://cdn.beacons.ai/user_content/0pFto0dcXUPv1y1pur3Y9kb6pCC2/link_images/9b234a39-0272-4c0a-b38a-13a72e4efc2c.png"
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { },
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
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { },
                    color = Color(0xFF2478DF),
                    elevation = 4.dp
                ) {
                    Text(
                        text = "Alterar",
                        Modifier.padding(10.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { },
                    color = Color(0xFFD82D2D),
                    elevation = 4.dp
                ) {
                    Text(
                        text = "Remover",
                        Modifier.padding(10.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun FormTextField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    placeholder: String = ""
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        Modifier.fillMaxWidth(),
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