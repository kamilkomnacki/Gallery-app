package komnacki.gallery.ui.gallery

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import komnacki.gallery.App
import komnacki.gallery.domain.model.Image
import komnacki.gallery.domain.usecase.GetImagesUseCase
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel
@Inject
constructor(
    app: App,
    private val getImagesUseCase: GetImagesUseCase
) : AndroidViewModel(app) {

    val images : LiveData<PagingData<Image>> = fetchImages()
    val selected = MutableLiveData<Image>()

    private fun fetchImages() : LiveData<PagingData<Image>> {
        return getImagesUseCase.getImages()
            .cachedIn(viewModelScope)
    }

    fun isUserLogIn() : Boolean = getImagesUseCase.isUserLogIn()

    fun select(image: Image) {
        selected.value = image
    }
}