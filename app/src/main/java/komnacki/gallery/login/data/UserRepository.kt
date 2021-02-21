package komnacki.gallery.login.data

import io.reactivex.Completable

interface UserRepository {
    fun logIn(username: String, password: String): Completable
    fun isLogIn() : Boolean
}
