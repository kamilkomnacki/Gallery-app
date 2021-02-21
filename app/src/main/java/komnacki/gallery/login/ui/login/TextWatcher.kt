package komnacki.gallery.login.ui.login

import android.text.Editable

class TextWatcher(private val onAfterTextChanged: (String) -> Unit) : android.text.TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        onAfterTextChanged.invoke(editable.toString())
    }
}