package komnacki.gallery.login.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import komnacki.gallery.login.data.network.UnsplashApiService
import komnacki.gallery.login.data.network.UnsplashImage
import komnacki.gallery.login.data.paging.UnsplashImagePagingSource
import komnacki.gallery.login.domain.Image
import komnacki.gallery.login.domain.ImageRepository

class ImageRepository_Impl(
    private val service: UnsplashApiService,
    private val mapper: ImageMapper
) : ImageRepository {

    private val pageSize = 10

    override suspend fun search(token: String, page: Int, query: String): List<Image> {
        return mapper.toDomainList(
            service.search(token, query, page, pageSize).results
        )
    }

    override suspend fun get(token: String, id: Int): Image {
        TODO("Not yet implemented")
    }

    override fun get(pagingConfig: PagingConfig): LiveData<PagingData<UnsplashImage>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { UnsplashImagePagingSource(service)}
        ).liveData
    }

    override fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
            enablePlaceholders = true)
    }
}