package es.jco.data.source

import es.jco.data.common.Response
import es.jco.domain.User

interface RemoteDataSource {

    suspend fun getUsers() : Response<List<User>>
    suspend fun createUser(user: User): Response<User>
    suspend fun updateUser(user: User): Response<User>
    suspend fun deleteUser(userId: Long): Response<Boolean>
}