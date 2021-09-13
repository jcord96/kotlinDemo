package es.jco.demo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AddressDTOResult(
    val result: List<AddressDTO>
)

@Parcelize
data class AddressDTO(
    @SerializedName("street") var street: String?,
    @SerializedName("suite") var suite: String?,
    @SerializedName("city") var city: String?,
    @SerializedName("zipcode") var zipcode: String?,
    @SerializedName("geo") var geoDTO: GeoDTO?
) : Parcelable