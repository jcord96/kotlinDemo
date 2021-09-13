package es.jco.demo.data.database.dao

import androidx.room.*
import es.jco.demo.data.database.entity.AddressEntity
import es.jco.demo.data.database.entity.AddressParentEntity

@Dao
interface AddressDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(address: AddressEntity): Long

    @Transaction
    @Query("SELECT * FROM AddressEntity WHERE addressId = :addressId")
    suspend fun getAddress(addressId: Long): AddressParentEntity?
}