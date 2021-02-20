package komnacki.gallery.login.ui

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import komnacki.gallery.login.App
import komnacki.gallery.login.domain.ImageRepository
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel
@Inject
constructor(
    app: App,
    private val repository: ImageRepository,
) : AndroidViewModel(app) {

    val images : LiveData<PagingData<String>> = fetchImages()

    private fun fetchImages() : LiveData<PagingData<String>> {
        return repository.get()
            .map { it.map { it.urls.small } }
            .cachedIn(viewModelScope)
    }
}