package komnacki.gallery.login.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import komnacki.gallery.login.data.network.UnsplashApiService
import komnacki.gallery.login.data.network.UnsplashImage
import komnacki.gallery.login.data.paging.UnsplashImagePagingSource
import komnacki.gallery.login.domain.ImageRepository

class ImageRepositoryImpl(
    private val service: UnsplashApiService,
    private val mapper: ImageMapper
) : ImageRepository {

    override fun search(pagingConfig: PagingConfig): LiveData<PagingData<UnsplashImage>> {
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