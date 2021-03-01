package komnacki.gallery.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import komnacki.gallery.domain.ImageRepository
import komnacki.gallery.domain.UserRepository
import komnacki.gallery.domain.model.Image
import javax.inject.Inject

class GetImagesUseCase
@Inject constructor(
    private val imageRepository : ImageRepository,
    private val userRepository: UserRepository
) {
    fun getImages() : LiveData<PagingData<Image>> {
        return imageRepository.search()
    }

    fun isUserLogIn() : Boolean {
        return userRepository.isLogIn()
    }
}