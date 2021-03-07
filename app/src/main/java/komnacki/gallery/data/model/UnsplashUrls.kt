package komnacki.gallery.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashUrls(
    @SerializedName("small")
    val small: String,
    @SerializedName("full")
    val full: String
) : Parcelable