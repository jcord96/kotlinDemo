package es.jco.demo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.jco.demo.data.database.dao.AddressDao
import es.jco.demo.data.database.dao.CompanyDao
import es.jco.demo.data.database.dao.GeoDao
import es.jco.demo.data.database.dao.UserDao
import es.jco.demo.data.database.entity.User

@Database(
    entities = [User::class],
    version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun addressDao(): AddressDao
    abstract fun companyDao(): CompanyDao
    abstract fun geoDao(): GeoDao
    abstract fun userDao(): UserDao
}