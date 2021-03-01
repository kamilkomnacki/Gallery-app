package komnacki.gallery.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import komnacki.gallery.data.ImageRepositoryImpl
import komnacki.gallery.data.UserRepositoryImpl
import komnacki.gallery.data.mapper.ImageMapper
import komnacki.gallery.data.network.UnsplashApiService
import komnacki.gallery.domain.ImageRepository
import komnacki.gallery.domain.UserRepository
import komnacki.gallery.rxutils.SchedulerProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideImageRepository(
        unsplashImageService: UnsplashApiService,
        imageMapper: ImageMapper
    ): ImageRepository {
        return ImageRepositoryImpl(
            mapper = imageMapper,
            service = unsplashImageService
        )
    }

    @Singleton
    @Provides
    fun provideLoginRepository(): UserRepository {
        return UserRepositoryImpl(
            schedulerProvider = SchedulerProvider()
        )
    }
}