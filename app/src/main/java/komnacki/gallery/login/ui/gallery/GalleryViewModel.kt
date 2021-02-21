package komnacki.gallery.login.ui.gallery

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import komnacki.gallery.login.App
import komnacki.gallery.login.domain.ImageRepository
import komnacki.gallery.login.domain.model.Image
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel
@Inject
constructor(
    app: App,
    private val repository: ImageRepository
) : AndroidViewModel(app) {

    val images : LiveData<PagingData<Image>> = fetchImages()

    private fun fetchImages() : LiveData<PagingData<Image>> {
        return repository.search()
            .cachedIn(viewModelScope)
    }
}