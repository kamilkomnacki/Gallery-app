package komnacki.gallery.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize;

@Parcelize
data class UnsplashUser(
    val username: String
) : Parcelable