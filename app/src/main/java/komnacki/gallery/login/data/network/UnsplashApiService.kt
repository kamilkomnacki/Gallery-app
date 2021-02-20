package komnacki.gallery.login.data.network

import io.reactivex.Completable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface UnsplashApiService {
    @GET("search/photos")
    suspend fun search(
        @Query("client_id") clientId: String,
        @Query("query") query: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int
    ): SearchResponse

    @GET
    fun trackDownload(@Url url: String): Completable

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val API_TOKEN = "CGMrIUppQWgHAOi6sy-beLfhcCyNm1NKUW7SnX_vspc"
    }
}