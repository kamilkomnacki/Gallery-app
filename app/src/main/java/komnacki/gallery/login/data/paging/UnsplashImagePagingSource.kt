package komnacki.gallery.login.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import komnacki.gallery.login.data.ImageMapper
import komnacki.gallery.login.data.network.UnsplashApiService
import komnacki.gallery.login.domain.model.Image
import retrofit2.HttpException
import java.io.IOException

class UnsplashImagePagingSource(
    private val service: UnsplashApiService,
    private val mapper: ImageMapper) : PagingSource<Int, Image>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val DEFAULT_PAGE_INDEX = 1
        val page = params.key ?: DEFAULT_PAGE_INDEX

        return try {
            val response = service.search(UnsplashApiService.API_TOKEN, "góry", page, params.loadSize)
            LoadResult.Page(
                data = mapper.toDomainList(response.results),
                prevKey = if(page == DEFAULT_PAGE_INDEX) { null } else { page - 1 },
                nextKey = if(response.results.isEmpty()) { null } else { page + 1 }
            )
        } catch (exception : IOException) {
            return LoadResult.Error(exception)
        } catch (exception : HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition
    }

}