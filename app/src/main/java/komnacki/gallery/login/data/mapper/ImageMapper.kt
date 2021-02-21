package komnacki.gallery.login.data.mapper

import komnacki.gallery.login.data.model.UnsplashImage
import komnacki.gallery.login.data.model.UnsplashUrls
import komnacki.gallery.login.data.model.UnsplashUser
import komnacki.gallery.login.domain.model.Image
import komnacki.gallery.login.domain.model.Urls
import komnacki.gallery.login.domain.model.User

class ImageMapper : DomainMapper<UnsplashImage, Image> {
    override fun mapToDomainModel(model: UnsplashImage): Image {
        return Image(
            id = model.id,
            width = model.width,
            height = model.height,
            description = model.description,
            urls = mapToDomainUrlsModel(model.urls),
            user = mapToDomainUser(model.user)
        )
    }

    private fun mapToDomainUrlsModel(model : UnsplashUrls) : Urls {
        return Urls(
            small = model.small,
            full = model.full
        )
    }

    private fun mapToDomainUser(model : UnsplashUser) : User {
        return User(
            id = model.id,
            username = model.username
        )
    }

    override fun mapFromDomainModel(domainModel: Image): UnsplashImage {
        return UnsplashImage(
            id = domainModel.id,
            width = domainModel.width,
            height = domainModel.height,
            description = domainModel.description,
            urls = mapFromDomainUrlsModel(domainModel.urls),
            user = mapToDomainUser(domainModel.user)
        )
    }

    private fun mapFromDomainUrlsModel(model : Urls) : UnsplashUrls {
        return UnsplashUrls(
            small = model.small,
            full = model.full
        )
    }

    private fun mapToDomainUser(model : User) : UnsplashUser {
        return UnsplashUser(
            id = model.id,
            username = model.username
        )
    }

    fun toDomainList(initial: List<UnsplashImage>): List<Image>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Image>): List<UnsplashImage>{
        return initial.map { mapFromDomainModel(it) }
    }
}