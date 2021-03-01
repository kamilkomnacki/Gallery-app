package komnacki.gallery.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import komnacki.gallery.domain.ImageRepository
import komnacki.gallery.domain.UserRepository
import komnacki.gallery.domain.usecase.GetImagesUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideGetImagesUseCase(
        imageRepository: ImageRepository,
        userRepository: UserRepository
    ): GetImagesUseCase {
        return GetImagesUseCase(
            imageRepository = imageRepository,
            userRepository = userRepository
        )
    }
}