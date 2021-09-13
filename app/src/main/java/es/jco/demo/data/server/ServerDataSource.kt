package es.jco.demo.data.server

import es.jco.data.common.Response
import es.jco.data.source.RemoteDataSource
import es.jco.demo.data.server.mapper.toDomain
import es.jco.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ServerDataSource @Inject constructor(private val apiService: APIService) : RemoteDataSource {

    override suspend fun getUsers(): Response<List<User>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUsers()
                Response(value = response.body()?.map { userDTO -> userDTO.toDomain() } ?: emptyList())
            } catch (e: Exception) {
                Response(error = e.cause)
            }
        }
    }

}