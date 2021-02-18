package komnacki.gallery.login.network

data class Image (
    val id: String,
    val width: Int,
    val height: Int,
    val color: String? = "#000000",
    val description: String?,
    val urls: UnsplashUrls,
    val user: UnsplashUser
)
