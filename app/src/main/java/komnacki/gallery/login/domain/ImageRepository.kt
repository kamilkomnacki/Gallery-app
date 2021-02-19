package komnacki.gallery.login.domain

interface ImageRepository {

    suspend fun search(token: String, page: Int, query: String): List<Image>

    suspend fun get(token: String, id: Int): Image
}