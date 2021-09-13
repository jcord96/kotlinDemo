package es.jco.demo.data.database.dao

import androidx.room.*
import es.jco.demo.data.database.entity.UserEntity
import es.jco.demo.data.database.entity.UserParentEntity

@Dao
interface UserDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM UserEntity")
    suspend fun getUsers(): List<UserParentEntity>
}