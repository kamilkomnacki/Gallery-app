package komnacki.gallery.login.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import komnacki.gallery.login.data.ImageMapper
import komnacki.gallery.login.data.network.UnsplashApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideUnsplashService(): UnsplashApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(UnsplashApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideImageMapper(): ImageMapper {
        return ImageMapper()
    }

    @Singleton
    @Provides
//    @Named("token")
    fun provideAuthToken(): String{
        return "Token"
    }
}