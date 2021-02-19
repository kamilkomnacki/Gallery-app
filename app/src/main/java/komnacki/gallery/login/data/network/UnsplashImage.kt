package komnacki.gallery.login.data.network

data class UnsplashImage (
    val id: String,
    val width: Int,
    val height: Int,
    val color: String? = "#000000",
    val description: String?,
    val urls: UnsplashUrls,
    val user: UnsplashUser
)
