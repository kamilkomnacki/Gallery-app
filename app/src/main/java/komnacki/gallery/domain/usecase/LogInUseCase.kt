package komnacki.gallery.domain.usecase

import io.reactivex.Completable
import komnacki.gallery.Constants
import komnacki.gallery.domain.UserRepository
import komnacki.gallery.rxutils.SchedulerProvider
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun invoke(username: String, password: String): Completable {
        return userRepository.logIn(username, password)
            .timeout(Constants.LOGIN_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .subscribeOn(SchedulerProvider().io)
            .observeOn(SchedulerProvider().mainThread)
    }
}