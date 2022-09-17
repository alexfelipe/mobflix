package br.com.alura.mobflix.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.lifecycleScope
import br.com.alura.mobflix.sampleData.saveVideo
import br.com.alura.mobflix.ui.screens.FormVideoScreen
import br.com.alura.mobflix.ui.theme.MobflixTheme
import kotlinx.coroutines.launch

class FormVideoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobflixTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val videoId = intent.getStringExtra("VIDEO_ID")
                    FormVideoScreen(videoId = videoId, onRegisterClick = { video ->
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