package es.jco.demo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Geo (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val lat: Long?,
    val lng: Long?
)