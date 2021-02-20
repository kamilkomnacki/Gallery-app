package komnacki.gallery.login.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashUrls(
    val thumb: String?,
    val small: String,
    val regular: String?,
    val full: String?,
    val raw: String?
) : Parcelable