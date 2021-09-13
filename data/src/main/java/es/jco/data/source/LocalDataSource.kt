package es.jco.data.source

import es.jco.domain.User

interface LocalDataSource {

    suspend fun insertUser(user: User)
    suspend fun insertUsers(users: List<User>)
    suspend fun getUsers(): List<User>
}