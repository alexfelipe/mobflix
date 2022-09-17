package br.com.alura.mobflix.sampleData

import android.util.Log
import br.com.alura.mobflix.model.Category
import br.com.alura.mobflix.model.YoutubeVideo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

private val sampleVideos = mutableListOf(
    YoutubeVideo(

        youtubeId = "2z6f8VN_Pro",
        category = Category.MOBILE
    ),
    YoutubeVideo(
        youtubeId = "6xBGk2HdeUg",
        category = Category.DATA_SCIENCE
    ),
    YoutubeVideo(
        youtubeId = "FkWL85nSGks",
        category = Category.PROGRAMACAO
    ),
    YoutubeVideo(
        youtubeId = "6IuQUgeDPg0",
        category = Category.FRONT_END
    ),
    YoutubeVideo(
        youtubeId = "412nsNqL8YE",
        category = Category.MOBILE
    ),
    YoutubeVideo(
        youtubeId = "XKpUHBWWXFY",
        category = Category.MOBILE
    ),
)

private val videosMutableFlow = MutableStateFlow(sampleVideos.toList())

val videosFlow: Flow<List<YoutubeVideo>> = videosMutableFlow

suspend fun saveVideo(video: YoutubeVideo) {
    sampleVideos.indexOfFirst {
        video.id == it.id
    }.let { index ->
        if(index > -1){
            sampleVideos[index] = video
        } else {
            sampleVideos.add(video)
        }
    }
    val videos = sampleVideos.toList()
    videosMutableFlow.emit(videos)
}

fun findYoutubeVideoById(id: String): YoutubeVideo? {
    return sampleVideos.find {
        it.id == id
    }
}