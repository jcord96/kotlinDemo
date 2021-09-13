package es.jco.demo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GeoEntity(
    @PrimaryKey(autoGenerate = true) val geoId: Long?,
    val lat: Double?,
    val lng: Double?,
    val addressId: Long?
)