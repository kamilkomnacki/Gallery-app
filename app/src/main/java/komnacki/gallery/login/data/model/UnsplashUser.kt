package komnacki.gallery.login.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize;

@Parcelize
data class UnsplashUser(
    val id: String,
    val username: String
) : Parcelable