package komnacki.gallery.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppUser(
    @SerializedName("username")
    val username: String,
) : Parcelable