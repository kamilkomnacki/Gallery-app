package komnacki.gallery.login.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import komnacki.gallery.login.data.ImageMapper
import komnacki.gallery.login.data.ImageRepositoryImpl
import komnacki.gallery.login.data.UserRepository
import komnacki.gallery.login.data.UserRepositoryImpl
import komnacki.gallery.login.data.network.UnsplashApiService
import komnacki.gallery.login.domain.ImageRepository
import komnacki.gallery.login.rxutils.SchedulerProvider
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
        return ImageRepositoryImpl(
            mapper = imageMapper,
            service = unsplashImageService
        )
    }

    @Singleton
    @Provides
    fun provideLoginRepository(): UserRepository{
        return UserRepositoryImpl(
            schedulerProvider = SchedulerProvider()
        )
    }
}