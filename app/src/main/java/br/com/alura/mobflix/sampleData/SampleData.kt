package br.com.alura.mobflix.sampleData

import br.com.alura.mobflix.model.Category
import br.com.alura.mobflix.model.YoutubeVideo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

private val sampleVideos = mutableListOf(
    YoutubeVideo(
        "2z6f8VN_Pro",
        Category.MOBILE
    ),
    YoutubeVideo(
        "6xBGk2HdeUg",
        Category.DATA_SCIENCE
    ),
    YoutubeVideo(
        "FkWL85nSGks",
        Category.PROGRAMACAO
    ),
    YoutubeVideo(
        "6IuQUgeDPg0",
        Category.FRONT_END
    ),
    YoutubeVideo(
        "412nsNqL8YE",
        Category.MOBILE
    ),
    YoutubeVideo(
        "XKpUHBWWXFY",
        Category.MOBILE
    ),
)

private val _videosFlow = MutableStateFlow(sampleVideos.toList())

val videosFlow: Flow<List<YoutubeVideo>> = _videosFlow

suspend fun saveVideo(video: YoutubeVideo) {
    sampleVideos.add(video)
    _videosFlow.emit(sampleVideos.toList())
}