package komnacki.gallery.login.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import komnacki.gallery.login.domain.model.Image

interface ImageRepository {

    fun search(pagingConfig: PagingConfig = getDefaultPageConfig()) : LiveData<PagingData<Image>>

    fun getDefaultPageConfig(): PagingConfig
}