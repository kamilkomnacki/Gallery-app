package komnacki.gallery.login.ui

import android.util.Log
import androidx.lifecycle.*
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
    private val app: App,
    private val repository: ImageRepository,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {

    val images : LiveData<PagingData<String>> = fetchImages()

    fun fetchImages() : LiveData<PagingData<String>> {
        return repository.get()
            .map { it.map { it.urls.small } }
            .cachedIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("KK: ", "GalleryViewModel onCleared")
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