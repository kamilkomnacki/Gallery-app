package komnacki.gallery.domain.model

data class Image(
    val id: String,
    val description: String?,
    val urls: Urls,
    val user: User
)
