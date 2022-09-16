package br.com.alura.mobflix.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.mobflix.ui.screens.HomeScreen
import br.com.alura.mobflix.ui.theme.MobflixTheme
import br.com.alura.mobflix.ui.theme.topAppBarFont

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                onFabClick = {
                    Intent(
                        this,
                        FormVideoActivity::class.java
                    ).apply { startActivity(this) }
                },
            ) {
                HomeScreen(Modifier.padding(it))
            }
        }
    }
}

@Composable
fun App(
    onFabClick: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    MobflixTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(topBar = {
                TopAppBar(
                    backgroundColor = Color(0xFF1A1A1A),
                    contentPadding = PaddingValues(top = 16.dp)
                ) {
                    Box(Modifier.fillMaxSize()) {
                        Text(
                            text = "MOBFLIX",
                            Modifier
                                .padding(bottom = 8.dp)
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter),
                            textAlign = TextAlign.Center,
                            style = LocalTextStyle.current.copy(
                                color = Color(0xFF2478DF)
                            ),
                            fontSize = 32.sp,
                            fontFamily = topAppBarFont
                        )
                    }
                }
            }, floatingActionButton = {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null,
                    )
                }
            }) { padding ->
                content(padding)
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App {
        HomeScreen(Modifier.padding(it))
    }
}