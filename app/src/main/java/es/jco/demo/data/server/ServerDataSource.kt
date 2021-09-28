package es.jco.demo.data.server

import es.jco.data.common.Response
import es.jco.data.source.RemoteDataSource
import es.jco.demo.data.server.mapper.toDTO
import es.jco.demo.data.server.mapper.toDomain
import es.jco.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Server data source
 *
 * @property apiService
 * @constructor Create empty Server data source
 */
class ServerDataSource @Inject constructor(private val apiService: APIService) : RemoteDataSource {

    companion object {
        private val requestDispatcher: CoroutineDispatcher = Dispatchers.IO
    }

    /**
     * Request to get users
     *
     * @return
     */
    override suspend fun getUsers(): Response<List<User>> {
        return withContext(requestDispatcher) {
            try {
                val response = apiService.getUsers()
                Response(value = response.body()?.map { userDTO -> userDTO.toDomain() } ?: emptyList())
            } catch (e: Exception) {
                Response(error = e.cause)
            }
        }
    }

    /**
     * Request to create a user
     *
     * @param user
     * @return
     */
    override suspend fun createUser(user: User): Response<User> {
        return withContext(requestDispatcher) {
            try {
                val response = apiService.postUser(user.toDTO())
                if (response.isSuccessful) {
                    Response(value = response.body()?.toDomain())
                } else {
                    Response(error = Exception("Request was unsuccessful"))
                }
            } catch (e: Exception) {
                Response(error = e.cause)
            }
        }
    }

    /**
     * Request to update a user
     *
     * @param user
     * @return
     */
    override suspend fun updateUser(user: User): Response<User> {
        return withContext(requestDispatcher) {
            try {
                val response = apiService.putUser(user.id.toString(), user.toDTO())
                if (response.isSuccessful) {
                    Response(value = response.body()?.toDomain())
                } else {
                    Response(error = Exception("Request was unsuccessful"))
                }
            } catch (e: Exception) {
                Response(error = e.cause)
            }
        }
    }

    /**
     * Request to delete a user
     *
     * @param userId
     * @return
     */
    override suspend fun deleteUser(userId: Long): Response<Boolean> {
        return withContext(requestDispatcher) {
            try {
                val response = apiService.deleteUser(userId.toString())
                if (response.isSuccessful) {
                    Response(value = true)
                } else {
                    Response(error = Exception("Request was unsuccessful"))
                }
            } catch (e: Exception) {
                Response(error = e.cause)
            }
        }
    }
}