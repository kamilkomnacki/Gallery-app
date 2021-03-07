package komnacki.gallery.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashImage (
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("urls")
    val urls: UnsplashUrls,
    @SerializedName("user")
    val user: UnsplashUser
) : Parcelable
