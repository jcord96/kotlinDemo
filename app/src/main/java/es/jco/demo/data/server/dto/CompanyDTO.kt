package es.jco.demo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CompanyDTOResult(
    val result: List<CompanyDTO>
)

@Parcelize
data class CompanyDTO(
    @SerializedName("name") var name: String?,
    @SerializedName("catchPhrase") var catchPhrase: String?,
    @SerializedName("bs") var bs: String?
) : Parcelable