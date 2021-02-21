package komnacki.gallery.login.ui.gallery

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import komnacki.gallery.login.App
import komnacki.gallery.login.domain.ImageRepository
import komnacki.gallery.login.domain.UserRepository
import komnacki.gallery.login.domain.model.Image
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel
@Inject
constructor(
    app: App,
    private val imageRepository: ImageRepository,
    private val userRepository : UserRepository
) : AndroidViewModel(app) {

    val images : LiveData<PagingData<Image>> = fetchImages()
    val selected = MutableLiveData<Image>()

    private fun fetchImages() : LiveData<PagingData<Image>> {
        return imageRepository.search()
            .cachedIn(viewModelScope)
    }

    fun isUserLogIn() : Boolean = userRepository.isLogIn()
    fun select(image: Image) {
        selected.value = image
    }
}