package es.jco.demo.data.server

import es.jco.demo.data.server.dto.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import java.util.*

interface APIService {

    @Headers("Accept: application/json")
    @GET
    suspend fun getUsers(): Response<List<UserDTO>>
}