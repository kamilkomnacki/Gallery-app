package komnacki.gallery.login.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import komnacki.gallery.login.data.network.UnsplashApiService
import komnacki.gallery.login.data.network.UnsplashImage
import retrofit2.HttpException
import java.io.IOException

class UnsplashImagePagingSource
    constructor(private val service: UnsplashApiService) : PagingSource<Int, UnsplashImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val DEFAULT_PAGE_INDEX = 1
        val page = params.key ?: DEFAULT_PAGE_INDEX

        return try {
            val response = service.search(UnsplashApiService.API_TOKEN, "góry", page, params.loadSize)
            LoadResult.Page(
                data = response.results,
                prevKey = if(page == DEFAULT_PAGE_INDEX) { null } else { page - 1 },
                nextKey = if(response.results.isEmpty()) { null } else { page + 1 }
            )
        } catch (exception : IOException) {
            return LoadResult.Error(exception)
        } catch (exception : HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition
    }

}