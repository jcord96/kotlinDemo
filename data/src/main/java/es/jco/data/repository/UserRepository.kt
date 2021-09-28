package es.jco.data.repository

import es.jco.data.common.ResultData
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource
import es.jco.domain.User
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun loadUsers(): ResultData<Boolean> {
        return try {
            ResultData.success(remoteDataSource.getUsers().getValue().let {
                if (!it.isNullOrEmpty()) {
                    localDataSource.insertUsers(it)
                }

                !localDataSource.getUsers().isNullOrEmpty()
            })
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }

    suspend fun getUsers(): ResultData<Flow<List<User>>> {
        return try {
            ResultData.success(localDataSource.getUsersUpdatable())
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }

    suspend fun getUser(userId: Long): ResultData<User> {
        return try {
            ResultData.success(localDataSource.getUserById(userId))
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }

    suspend fun saveUser(user: User): ResultData<User> {
        return try {
            val result = if (user.id == null)  remoteDataSource.createUser(user).getValue() else remoteDataSource.updateUser(user).getValue()
            if (result != null) {
                localDataSource.insertUser(result)
                ResultData.success(result)
            } else {
                throw Exception("Null request result")
            }
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }

    suspend fun deleteUser(userId: Long): ResultData<Boolean> {
        return try {
            if (remoteDataSource.deleteUser(userId).getValue() == true) {
                localDataSource.deleteUser(userId)
                ResultData.Success(true)
            } else {
                throw Exception("User didn't delete")
            }
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}