package komnacki.gallery.data.network

import komnacki.gallery.data.model.UnsplashImage

data class SearchResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<UnsplashImage>
)