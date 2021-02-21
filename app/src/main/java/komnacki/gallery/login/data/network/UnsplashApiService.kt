package komnacki.gallery.login.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("search/photos")
    suspend fun search(
        @Query("client_id") clientId: String,
        @Query("query") query: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int
    ): SearchResponse

    companion object {
        const val API_TOKEN = "CGMrIUppQWgHAOi6sy-beLfhcCyNm1NKUW7SnX_vspc"
    }
}