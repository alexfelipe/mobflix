package br.com.alura.mobflix.model

data class YoutubeVideo(
    val id: String = "",
    val category: Category?,
) {
    val thumb = "https://img.youtube.com/vi/$id/0.jpg"
}