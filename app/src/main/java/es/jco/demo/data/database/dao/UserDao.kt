package es.jco.demo.data.database.dao

import androidx.room.*
import es.jco.demo.data.database.entity.UserEntity
import es.jco.demo.data.database.entity.UserParentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>): List<Long>

    @Transaction
    @Query("SELECT * FROM UserEntity WHERE userId = :userId")
    suspend fun getUserById(userId: Long): UserParentEntity

    @Transaction
    @Query("SELECT * FROM UserEntity")
    suspend fun getUsers(): List<UserParentEntity>

    @Transaction
    @Query("SELECT * FROM UserEntity")
    fun getUsersUpdatable(): Flow<List<UserParentEntity>>

    @Transaction
    @Query("DELETE FROM UserEntity WHERE userId = :userId")
    fun deleteUser(userId: Long)
}