package komnacki.gallery.login.ui.login

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import komnacki.gallery.R
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private var usernameTextWatcher = addTextWatcher()
    private var passwordTextWatcher = addTextWatcher()
    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            btn_login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                tv_username.error = getString(loginState.usernameError)
            } else {
                tv_username.error = null
            }

            if (loginState.passwordError != null) {
                tv_password.error = getString(loginState.passwordError)
            } else {
                tv_password.error = null
            }
        })

        viewModel.loginResult.observe(viewLifecycleOwner, Observer {
            val loginResult = it ?: return@Observer

            if(loginResult.isPending == true) {
                onLoginPending()
            } else {
                onLoginTerminate()
                if (loginResult.success != null) {
                    onLoginSuccess(loginResult.success)
                }
                if (loginResult.error != null) {
                    onLoginFailed(loginResult.error)
                }
            }
        })

        et_password.setOnEditorActionListener { _, actionId, _ ->
            if (btn_login.isEnabled) {
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        onLoginStart()
                    }
                }
            }
            false
        }

        btn_login.setOnClickListener {
            onLoginStart()
        }
        btn_cancel.setOnClickListener {
            onLoginCancel()
        }
    }

    private fun onLoginStart() {
        viewModel.login(et_username.text.toString(), et_password.text.toString())
    }

    private fun onLoginCancel() {
        viewModel.cancel()
    }

    private fun onLoginTerminate() {
        et_username.isEnabled = true
        et_password.isEnabled = true
        progress_login.visibility = View.INVISIBLE
        btn_cancel.visibility = View.INVISIBLE
        btn_login.visibility = View.VISIBLE
    }

    private fun onLoginPending() {
        et_username.isEnabled = false
        et_password.isEnabled = false
        progress_login.visibility = View.VISIBLE
        btn_cancel.visibility = View.VISIBLE
        btn_login.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        et_username.addTextChangedListener(usernameTextWatcher)
        et_password.addTextChangedListener(passwordTextWatcher)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        et_username.removeTextChangedListener(usernameTextWatcher)
        et_username.removeTextChangedListener(passwordTextWatcher)
    }

    private fun onLoginSuccess(@StringRes successString: Int) {
        Toast.makeText(requireContext(), successString, Toast.LENGTH_SHORT).show()
        this.findNavController().navigate(R.id.toDetailFragment)
    }

    private fun onLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(requireContext(), errorString, Toast.LENGTH_SHORT).show()
    }

    private fun addTextWatcher(): TextWatcher {
        return TextWatcher {
            viewModel.loginDataChanged(
                et_username.text.toString(),
                et_password.text.toString()
            )
        }
    }
}
