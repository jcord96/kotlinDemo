package es.jco.demo.data.database

import es.jco.data.source.LocalDataSource
import es.jco.demo.data.database.mapper.toDomain
import es.jco.demo.data.database.mapper.toEntity
import es.jco.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Room data source
 *
 * @constructor
 *
 * @param appRoomDatabase
 */
class RoomDataSource @Inject constructor(appRoomDatabase: AppRoomDatabase) :
    LocalDataSource {

    private val addressDao = appRoomDatabase.addressDao()
    private val companyDao = appRoomDatabase.companyDao()
    private val geoDao = appRoomDatabase.geoDao()
    private val userDao = appRoomDatabase.userDao()

    /**
     * Function to insert a user
     *
     * @param user
     */
    override suspend fun insertUser(user: User) {
        user.toEntity().user.let { user.id = userDao.insert(it) }
        user.company?.toEntity(user.id)?.let { user.company!!.id = companyDao.insert(it) }
        user.address?.toEntity(user.id)?.address?.let { user.address!!.id = addressDao.insert(it) }
        user.address?.geo?.toEntity(user.address?.id)?.let { geoDao.insert(it) }
    }

    /**
     * Function to insert a users list
     *
     * @param users
     */
    override suspend fun insertUsers(users: List<User>) {
        users.forEach { insertUser(it) }
    }

    /**
     * Function to delete user by user id
     *
     * @param userId
     */
    override suspend fun deleteUser(userId: Long) {
        userDao.deleteUser(userId)
    }

    /**
     * Function to get user by id
     *
     * @param userId
     * @return user
     */
    override suspend fun getUserById(userId: Long): User = userDao.getUserById(userId).toDomain()

    /**
     * Function to get users
     *
     * @return users list
     */
    override suspend fun getUsers(): List<User> = userDao.getUsers().map { it.toDomain() }

    /**
     * Function to get live users
     *
     * @return flow with users list
     */
    override suspend fun getUsersUpdatable(): Flow<List<User>> =
        userDao.getUsersUpdatable().map { it.map { user -> user.toDomain() } }
}