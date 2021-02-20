package komnacki.gallery.login.domain

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import komnacki.gallery.login.data.network.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun search(token: String, page: Int, query: String): List<Image>

    suspend fun get(token: String, id: Int): Image

    fun letImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()) : Flow<PagingData<UnsplashImage>>

    fun getDefaultPageConfig(): PagingConfig
}