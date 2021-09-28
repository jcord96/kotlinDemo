package es.jco.data.source

import es.jco.domain.User
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getUserById(userId: Long): User
    suspend fun getUsers(): List<User>
    suspend fun getUsersUpdatable(): Flow<List<User>>

    suspend fun insertUser(user: User)
    suspend fun insertUsers(users: List<User>)

    suspend fun deleteUser(userId: Long)
}