package komnacki.gallery.login.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import komnacki.gallery.login.data.network.UnsplashImage

interface ImageRepository {

    suspend fun search(token: String, page: Int, query: String): List<Image>

    suspend fun get(token: String, id: Int): Image

    fun get(pagingConfig: PagingConfig = getDefaultPageConfig()) : LiveData<PagingData<UnsplashImage>>

    fun getDefaultPageConfig(): PagingConfig
}