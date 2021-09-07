package es.jco.demo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserDTOResult(
    val result: List<UserDTO>
)

@Parcelize
data class UserDTO (
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String?,
    @SerializedName("username") var username: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("address") var addressDTO: AddressDTO?,
    @SerializedName("phone") var phone: String?,
    @SerializedName("website") var website: String?,
    @SerializedName("company") var companyDTO: CompanyDTO?
) : Parcelable