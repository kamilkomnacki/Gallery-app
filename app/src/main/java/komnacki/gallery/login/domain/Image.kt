package komnacki.gallery.login.domain

//TODO: taki sam jak UnsplashImage
data class Image(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String? = "#000000",
    val description: String?,
    val urls: String, //small
    val user: String //name
)
