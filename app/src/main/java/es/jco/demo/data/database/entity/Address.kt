package es.jco.demo.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Address (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    @Embedded val geo: Geo?
)