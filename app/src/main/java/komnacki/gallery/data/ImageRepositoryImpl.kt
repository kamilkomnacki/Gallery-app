package komnacki.gallery.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import komnacki.gallery.Constants
import komnacki.gallery.data.mapper.ImageMapper
import komnacki.gallery.data.network.UnsplashApiService
import komnacki.gallery.data.paging.UnsplashImagePagingSource
import komnacki.gallery.domain.ImageRepository
import komnacki.gallery.domain.model.Image

class ImageRepositoryImpl(
    private val service: UnsplashApiService,
    private val mapper: ImageMapper
) : ImageRepository {

    override fun search(pagingConfig: PagingConfig): LiveData<PagingData<Image>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { UnsplashImagePagingSource(service, mapper) }
        ).liveData
    }

    override fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = Constants.API_PAGE_SIZE,
            initialLoadSize = Constants.API_PAGE_SIZE,
            enablePlaceholders = true)
    }
}