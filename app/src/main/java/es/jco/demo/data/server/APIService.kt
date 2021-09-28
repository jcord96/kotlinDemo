package es.jco.demo.data.server

import es.jco.demo.data.server.dto.UserDTO
import retrofit2.Response
import retrofit2.http.*

/**
 * API service
 *
 * @constructor Create empty API service
 */
interface APIService {

    @Headers(ServerConstants.HEADER_ACCEPT_TYPE, ServerConstants.HEADER_CONTENT_TYPE)
    @GET("/users")
    suspend fun getUsers(): Response<List<UserDTO>>

    @Headers(ServerConstants.HEADER_ACCEPT_TYPE, ServerConstants.HEADER_CONTENT_TYPE)
    @POST("/users")
    suspend fun postUser(@Body body: UserDTO): Response<UserDTO>

    @Headers(ServerConstants.HEADER_ACCEPT_TYPE, ServerConstants.HEADER_CONTENT_TYPE)
    @PUT("/users/{id}")
    suspend fun putUser(@Path("id") id: String, @Body body: UserDTO): Response<UserDTO>

    @Headers(ServerConstants.HEADER_ACCEPT_TYPE, ServerConstants.HEADER_CONTENT_TYPE)
    @DELETE("/users/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<Unit>
}