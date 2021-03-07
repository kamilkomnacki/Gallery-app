package komnacki.gallery.data.network

import com.google.gson.annotations.SerializedName
import komnacki.gallery.data.model.UnsplashImage

data class SearchResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<UnsplashImage>
)