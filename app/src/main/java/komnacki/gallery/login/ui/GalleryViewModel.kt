package komnacki.gallery.login.ui

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import komnacki.gallery.login.App
import komnacki.gallery.login.domain.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel
@Inject
constructor(
    private val app: App,
    private val repository: ImageRepository,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {

    fun fetchImages() : Flow<PagingData<String>> {
        return repository.letImagesFlow()
            .map { it.map { it.urls.small } }
            .cachedIn(viewModelScope)
    }
}


//@Inject
//constructor(
//    private val repository: ImageRepository,
//    private val token: String,
//    private val savedStateHandle: SavedStateHandle,
//) : AndroidViewModel() {
//    val images = Pager
//}