package komnacki.gallery.login.data

import komnacki.gallery.login.data.network.UnsplashImage
import komnacki.gallery.login.data.network.UnsplashUrls
import komnacki.gallery.login.data.network.UnsplashUser
import komnacki.gallery.login.domain.Image

class ImageMapper : DomainMapper<UnsplashImage, Image> {
    override fun mapToDomainModel(model: UnsplashImage): Image {
        return Image(
            id = model.id,
            width = model.width,
            height = model.height,
            description = model.description,
            urls = model.urls.small,
            user = model.user.username
        )
    }

    override fun mapFromDomainModel(domainModel: Image): UnsplashImage {
        return UnsplashImage(
            id = domainModel.id,
            width = domainModel.width,
            height = domainModel.height,
            color = "#000000",
            description = domainModel.description,
            urls = UnsplashUrls(null, domainModel.urls, null, null, null, null, null),
            user = UnsplashUser("0", domainModel.user)
        )
    }

    fun toDomainList(initial: List<UnsplashImage>): List<Image>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Image>): List<UnsplashImage>{
        return initial.map { mapFromDomainModel(it) }
    }
}