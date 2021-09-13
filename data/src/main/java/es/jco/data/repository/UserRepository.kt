package es.jco.data.repository

import es.jco.data.common.ResultData
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource
import es.jco.domain.User

class UserRepository (
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
    ) {

    suspend fun loadUsers() : ResultData<Boolean> {
        return try {
            ResultData.success( remoteDataSource.getUsers().getValue().let {
                if (!it.isNullOrEmpty()) {
                    localDataSource.insertUsers(it)
                }

                !localDataSource.getUsers().isNullOrEmpty()
            })
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }

    suspend fun getUsers() : ResultData<List<User>?> {
        return try {
            ResultData.success( localDataSource.getUsers() )
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }

}