package komnacki.gallery.login.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import komnacki.gallery.login.data.ImageMapper
import komnacki.gallery.login.data.ImageRepository_Impl
import komnacki.gallery.login.data.network.UnsplashApiService
import komnacki.gallery.login.domain.ImageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUnsplashImageRepository(
        unsplashImageService: UnsplashApiService,
        imageMapper: ImageMapper
    ): ImageRepository{
        return ImageRepository_Impl(
            mapper = imageMapper,
            service = unsplashImageService
        )
    }
}