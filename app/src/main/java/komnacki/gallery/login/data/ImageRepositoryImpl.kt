package komnacki.gallery.login.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import komnacki.gallery.login.data.network.UnsplashApiService
import komnacki.gallery.login.data.paging.UnsplashImagePagingSource
import komnacki.gallery.login.domain.ImageRepository
import komnacki.gallery.login.domain.model.Image

class ImageRepositoryImpl(
    private val service: UnsplashApiService,
    private val mapper: ImageMapper
) : ImageRepository {

    override fun search(pagingConfig: PagingConfig): LiveData<PagingData<Image>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { UnsplashImagePagingSource(service, mapper)}
        ).liveData
    }

    override fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
            enablePlaceholders = true)
    }
}