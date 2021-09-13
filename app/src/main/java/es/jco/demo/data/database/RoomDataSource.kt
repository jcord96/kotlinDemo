package es.jco.demo.data.database

import es.jco.data.source.LocalDataSource
import es.jco.demo.data.database.mapper.toDomain
import es.jco.demo.data.database.mapper.toEntity
import es.jco.domain.User
import javax.inject.Inject

class RoomDataSource @Inject constructor(appRoomDatabase: AppRoomDatabase) :
    LocalDataSource {

    private val addressDao = appRoomDatabase.addressDao()
    private val companyDao = appRoomDatabase.companyDao()
    private val geoDao = appRoomDatabase.geoDao()
    private val userDao = appRoomDatabase.userDao()

    override suspend fun insertUser(user: User) {
        user.toEntity().user.let { user.id = userDao.insert(it) }
        user.company?.toEntity(user.id)?.let { user.company!!.id = companyDao.insert(it) }
        user.address?.toEntity(user.id)?.address?.let { user.address!!.id = addressDao.insert(it) }
        user.address?.geo?.toEntity(user.address?.id)?.let { geoDao.insert(it) }
    }

    override suspend fun insertUsers(users: List<User>) {
        users.forEach { insertUser(it) }
    }

    override suspend fun getUsers(): List<User> = userDao.getUsers().map { it.toDomain() }
}