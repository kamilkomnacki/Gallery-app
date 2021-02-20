package komnacki.gallery.login

interface Model {
    val id: String
    val width: Int
    val height: Int
    val description: String?
    val urls : Urls
    val user: User
}

interface Urls {
    val thumb: String?
    val raw: String?
}

interface User {
    val name : String
}