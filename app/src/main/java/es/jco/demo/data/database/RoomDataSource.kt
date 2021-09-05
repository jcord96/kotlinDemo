package es.jco.demo.data.database

import es.jco.data.source.LocalDataSource
import javax.inject.Inject

class RoomDataSource @Inject constructor(private val appRoomDatabase: AppRoomDatabase) : LocalDataSource {

    private val userDao = appRoomDatabase.userDao()


}