package komnacki.gallery.data

import io.reactivex.Completable
import komnacki.gallery.data.model.AppUser
import komnacki.gallery.domain.UserRepository
import komnacki.gallery.rxutils.BaseSchedulerProvider
import java.util.concurrent.TimeUnit

class UserRepositoryImpl(
    val schedulerProvider: BaseSchedulerProvider
) : UserRepository {

    private var user: AppUser? = null

    private val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    override fun logIn(username: String, password: String): Completable {
        return Completable
            .timer(5, TimeUnit.SECONDS, schedulerProvider.computation)
            .andThen(Completable.fromAction {
                if (username != "nuck" || password != "chorris") {
                    throw Exception("401 Unauthorized :(")
                } else {
                    setLoggedInUser(AppUser(username))
                    Completable.complete()
                }
            })
    }

    override fun isLogIn(): Boolean = isLoggedIn

    /**
     * If user credentials will be cached in local storage, it is recommended it be encrypted
     * @see https://developer.android.com/training/articles/keystore
     * */
    private fun setLoggedInUser(loggedInUser: AppUser) {
        this.user = loggedInUser

    }
}