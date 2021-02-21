package komnacki.gallery.login.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashUrls(
    val small: String,
    val full: String?
) : Parcelable