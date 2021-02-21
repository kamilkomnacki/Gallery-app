package komnacki.gallery.domain

import io.reactivex.Completable

interface UserRepository {
    fun logIn(username: String, password: String): Completable
    fun isLogIn() : Boolean
}
