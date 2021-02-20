package komnacki.gallery.login.data.network

import komnacki.gallery.login.data.model.UnsplashImage

data class SearchResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<UnsplashImage>
)