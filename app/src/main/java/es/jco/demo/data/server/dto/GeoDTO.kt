package es.jco.demo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GeoDTOResult(
    val result: List<GeoDTO>
)

@Parcelize
data class GeoDTO (
    @SerializedName("lat") var lat: String?,
    @SerializedName("lng") var lng: String?
) : Parcelable