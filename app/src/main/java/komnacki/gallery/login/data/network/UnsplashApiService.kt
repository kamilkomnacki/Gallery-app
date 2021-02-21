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
}