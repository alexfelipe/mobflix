package br.com.alura.mobflix.model

import java.util.*

data class YoutubeVideo(
    val id: String = UUID.randomUUID().toString(),
    val youtubeId: String = "",
    val category: Category? = null,
) {
    val thumb = "https://img.youtube.com/vi/$youtubeId/0.jpg"
    val youtubeUrl = "https://www.youtube.com/watch?v=$youtubeId"
}