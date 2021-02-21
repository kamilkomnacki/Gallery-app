package komnacki.gallery.login.data

import io.reactivex.Completable
import komnacki.gallery.login.data.model.AppUser
import komnacki.gallery.login.rxutils.BaseSchedulerProvider
import java.util.concurrent.TimeUnit

class UserRepositoryImpl(
    val schedulerProvider: BaseSchedulerProvider
) : UserRepository {

    var user: AppUser? = null
        private set

    val isLoggedIn: Boolean
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

    /**
    * If user credentials will be cached in local storage, it is recommended it be encrypted
    * @see https://developer.android.com/training/articles/keystore
    * */
    private fun setLoggedInUser(loggedInUser: AppUser) {
        this.user = loggedInUser

    }
}