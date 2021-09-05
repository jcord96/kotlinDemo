package es.jco.demo.data.server.dto

import com.google.gson.annotations.SerializedName


data class UserDTO(
    @SerializedName("id") var id: Int
)