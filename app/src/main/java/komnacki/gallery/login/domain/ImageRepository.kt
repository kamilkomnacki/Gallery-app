package komnacki.gallery.login.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import komnacki.gallery.login.data.network.UnsplashImage

interface ImageRepository {

    fun search(pagingConfig: PagingConfig = getDefaultPageConfig()) : LiveData<PagingData<UnsplashImage>>

    fun getDefaultPageConfig(): PagingConfig
}