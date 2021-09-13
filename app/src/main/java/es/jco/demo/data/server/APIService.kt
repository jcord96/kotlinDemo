package es.jco.demo.data.server

import es.jco.demo.data.server.dto.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIService {

    @Headers("Accept: application/json")
    @GET("/users/")
    suspend fun getUsers(): Response<List<UserDTO>>
}