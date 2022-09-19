package br.com.alura.mobflix.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.alura.mobflix.model.Category
import br.com.alura.mobflix.model.YoutubeVideo
import br.com.alura.mobflix.sampleData.saveVideo
import br.com.alura.mobflix.sampleData.videosFlow
import br.com.alura.mobflix.ui.components.MobflixTopBar
import br.com.alura.mobflix.ui.screens.FormVideoScreen
import br.com.alura.mobflix.ui.screens.HomeScreen
import br.com.alura.mobflix.ui.theme.MobflixTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val scope = rememberCoroutineScope()
            App {
                Scaffold(topBar = {
                    currentDestination?.route?.let {
                        when (it) {
                            "home" -> MobflixTopBar()
                        }
                    }
                }, floatingActionButton = {
                    currentDestination?.route?.let {
                        when (it) {
                            "home" -> {
                                FloatingActionButton(onClick = {
                                    navController.navigate("formVideo")
                                }) {
                                    Icon(
                                        Icons.Default.Add,
                                        contentDescription = null,
                                    )
                                }
                            }
                        }
                    }
                }) {
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        Modifier.padding(it)
                    ) {
                        composable("home") {
                            val videos by videosFlow.collectAsState(initial = emptyList())
                            HomeScreen(videos = videos,
                                onClickVideo = { video ->
                                    openYoutubeVideo(video)
                                },
                                onLongClickVideo = { video ->
                                    navController.navigate("formVideo?videoId=${video.id}")
                                },
                                onPlayNowClick = { video ->
                                    openYoutubeVideo(video)
                                }
                            )
                        }
                        composable(
                            "formVideo?videoId={videoId}",
                            arguments = listOf(navArgument("videoId") { nullable = true })
                        ) { backStackEntry ->
                            val videoId = backStackEntry.arguments?.getString("videoId")
                            FormVideoScreen(videoId, onRegisterClick = { youtubeVideo ->
                                scope.launch {
                                    saveVideo(youtubeVideo)
                                    navController.popBackStack()
                                }
                            })
                        }
                    }
                }

            }
        }
    }


}

private fun Context.openYoutubeVideo(video: YoutubeVideo) {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(video.youtubeUrl)
        )
    )
}

@Composable
fun App(
    content: @Composable () -> Unit
) {
    MobflixTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App {
        HomeScreen(
            videos = listOf(
                YoutubeVideo(
                    category = Category.MOBILE
                ), YoutubeVideo(
                    category = Category.PROGRAMACAO,
                )
            )
        )
    }
}