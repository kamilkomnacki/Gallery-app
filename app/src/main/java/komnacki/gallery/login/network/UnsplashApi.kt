package komnacki.gallery.login.network

import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface UnsplashApi {
    @GET("search/photos")
    fun searchPhotos(
        @Query("client_id") clientId: String,
        @Query("query") query: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int
    ): Observable<Response<SearchResponse>>

    @GET
    fun trackDownload(@Url url: String): Completable

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }
}