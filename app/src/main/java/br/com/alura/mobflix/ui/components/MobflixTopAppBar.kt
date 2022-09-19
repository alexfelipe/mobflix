package br.com.alura.mobflix.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.mobflix.ui.theme.topAppBarFont

@Composable
fun MobflixTopBar() {
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
}
