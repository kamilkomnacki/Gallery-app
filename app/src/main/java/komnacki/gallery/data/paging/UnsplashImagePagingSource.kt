package komnacki.gallery.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import komnacki.gallery.Constants
import komnacki.gallery.data.mapper.ImageMapper
import komnacki.gallery.data.network.UnsplashApiService
import komnacki.gallery.domain.model.Image
import retrofit2.HttpException
import java.io.IOException

class UnsplashImagePagingSource(
    private val service: UnsplashApiService,
    private val mapper: ImageMapper
) : PagingSource<Int, Image>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val defaultPageIndex = 1
        val page = params.key ?: defaultPageIndex

        return try {
            val response = service.search(
                Constants.API_TOKEN,
                Constants.SEARCH_PHRASE,
                page,
                params.loadSize)
            LoadResult.Page(
                data = mapper.toDomainList(response.results),
                prevKey = if(page == defaultPageIndex) { null } else { page - 1 },
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