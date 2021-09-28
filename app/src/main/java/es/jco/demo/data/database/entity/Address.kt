package es.jco.demo.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class AddressEntity(
    @PrimaryKey(autoGenerate = true) var addressId: Long?,
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val userId: Long?
)

data class AddressParentEntity(
    @Embedded val address: AddressEntity?,
    @Relation(entity = GeoEntity::class, parentColumn = "addressId", entityColumn = "addressId") val geo: GeoEntity?
)