package komnacki.gallery.login.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashImage (
    val id: String,
    val width: Int,
    val height: Int,
    val description: String?,
    val urls: UnsplashUrls,
    val user: UnsplashUser
) : Parcelable
