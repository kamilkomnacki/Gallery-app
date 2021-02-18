package komnacki.gallery.login.network

data class SearchResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<Image>
)