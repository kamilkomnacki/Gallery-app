package komnacki.gallery.login.domain.model

data class Image(
    val id: String,
    val width: Int,
    val height: Int,
    val description: String?,
    val urls: Urls,
    val user: User
)
