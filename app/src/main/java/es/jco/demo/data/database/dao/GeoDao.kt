package es.jco.demo.data.database.dao

import androidx.room.*
import es.jco.demo.data.database.entity.GeoEntity

@Dao
interface GeoDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(geo: GeoEntity): Long

    @Transaction
    @Query("SELECT * FROM GeoEntity WHERE geoId = :geoId")
    suspend fun getGeo(geoId: Long): GeoEntity?
}