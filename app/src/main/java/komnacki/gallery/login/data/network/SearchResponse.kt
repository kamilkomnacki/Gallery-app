package komnacki.gallery.login.data.network

data class SearchResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<UnsplashImage>
)