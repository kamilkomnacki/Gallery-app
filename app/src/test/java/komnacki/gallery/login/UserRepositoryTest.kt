package komnacki.gallery.login

import io.reactivex.schedulers.TestScheduler
import komnacki.gallery.data.UserRepositoryImpl
import komnacki.gallery.rxutils.TestSchedulerProvider
import org.junit.Test
import java.util.concurrent.TimeUnit

class UserRepositoryTest {
    private val testScheduler = TestScheduler()
    private val userRepository = UserRepositoryImpl(TestSchedulerProvider(computation = testScheduler))

    @Test
    fun loginIncorrectCredentials() {
        val testObserver = userRepository.logIn("", "")
            .test()
            .assertNotTerminated()
            .assertNoErrors()

        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)

        testObserver.assertError(Throwable::class.java)
    }

    @Test
    fun loginIncorrectLogin() {
        val testObserver = userRepository.logIn("chuck", "chorris")
            .test()
            .assertNotTerminated()
            .assertNoErrors()

        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)

        testObserver.assertError(Throwable::class.java)
    }

    @Test
    fun loginIncorrectPassword() {
        val testObserver = userRepository.logIn("nuck", "norris")
            .test()
            .assertNotTerminated()
            .assertNoErrors()

        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)

        testObserver.assertError(Throwable::class.java)
    }

    @Test
    fun loginCorrectCredentials() {
        val testObserver = userRepository.logIn("nuck", "chorris")
            .test()
            .assertNotTerminated()
            .assertNoErrors()

        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)

        testObserver.assertComplete()
    }
}