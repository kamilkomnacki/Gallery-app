package komnacki.gallery.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize;

@Parcelize
data class UnsplashUser(
    @SerializedName("username")
    val username: String
) : Parcelable