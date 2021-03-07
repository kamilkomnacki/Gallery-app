package komnacki.gallery.data.model

import com.google.gson.annotations.SerializedName

data class AppUser(
    @SerializedName("username")
    val username: String,
)