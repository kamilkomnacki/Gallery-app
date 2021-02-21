package komnacki.gallery.ui.login

data class LoginResult(
        val success: Int? = null,
        val error: Int? = null,
        val isPending: Boolean? = false
)