package komnacki.gallery.login.data

import io.reactivex.Completable
import komnacki.gallery.login.rxutils.BaseSchedulerProvider
import java.util.concurrent.TimeUnit

class UserRepository(private val schedulerProvider: BaseSchedulerProvider) {
    fun logIn(username: String, password: String): Completable =
        Completable.timer(5, TimeUnit.SECONDS, schedulerProvider.computation)
            .andThen(Completable.fromAction {
                if (username != "nuck" || password != "chorris") {
                    throw Exception("401 Unauthorized :(")
                }
            })
}