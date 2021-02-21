package komnacki.gallery.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import komnacki.gallery.domain.model.Image

interface ImageRepository {

    fun search(pagingConfig: PagingConfig = getDefaultPageConfig()) : LiveData<PagingData<Image>>

    fun getDefaultPageConfig(): PagingConfig
}