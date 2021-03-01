package komnacki.gallery.ui.login

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.Disposable
import komnacki.gallery.App
import komnacki.gallery.Constants
import komnacki.gallery.R
import komnacki.gallery.domain.usecase.LogInUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    app: App,
    private val logInUseCase: LogInUseCase
) : AndroidViewModel(app) {

    private lateinit var loginJob: Disposable

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            Log.i(Constants.LOGIN_TAG, "Start sign in process")
            _loginResult.value = LoginResult(isPending = true)

            loginJob = logInUseCase.invoke(username, password)
                .doOnComplete {
                    Log.i(Constants.LOGIN_TAG, "Sign in successful")
                    _loginResult.value = LoginResult(
                        success = R.string.login_success,
                        isPending = false
                    )
                }
                .doOnDispose {
                    Log.i(Constants.LOGIN_TAG, "Cancel sign in process")
                    _loginResult.value = LoginResult(
                        isPending = false
                    )
                }
                .doOnError {
                    Log.e(Constants.LOGIN_TAG, "Error when try to sign in")
                    _loginResult.value = LoginResult(
                        error = R.string.login_failed,
                        isPending = false
                    )
                }
                .onErrorComplete()
                .subscribe {
                    /**Action already handled*/
                }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.login_invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.login_invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length in Constants.PASSWORD_MIN_LENGTH .. Constants.PASSWORD_MAX_LENGTH
    }

    fun cancel() {
        if (::loginJob.isInitialized && loginJob.isDisposed.not()) {
            loginJob.dispose()
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancel()
    }
}