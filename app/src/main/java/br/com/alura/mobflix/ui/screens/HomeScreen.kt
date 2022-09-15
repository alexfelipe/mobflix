package br.com.alura.mobflix.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.mobflix.ui.theme.MobflixTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
    ) {

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