package br.com.alura.mobflix.model

enum class Category(
    val id: String,
    val backgroundColor: Long,
    val textColor: Long = 0xFF000000
) {
    MOBILE("MOBILE", 0xFFffba0c),
    PROGRAMACAO("PROGRAMAÇÃO", 0xFF03c770),
    FRONT_END("FRONT-END", 0xFF6bd0fe),
    UX_DESIGN("UX & DESIGN", 0xFFdc6fbe),
    INOVACAO_GESTAO("INOVAÇÃO & GESTÃO", 0xFFff8c2c),
    DATA_SCIENCE("DATA SCIENCE", 0xFF9cd13c)
}
