package es.jco.data.source

import es.jco.data.common.Response
import es.jco.domain.User

interface RemoteDataSource {

    suspend fun getUsers() : Response<List<User>>
}