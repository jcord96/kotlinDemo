package es.jco.demo.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var userId: Long?,
    val name: String?,
    val username: String?,
    val email: String?,
    val phone: String?,
    val website: String?,
)

data class UserParentEntity(
    @Embedded val user: UserEntity,
    @Relation(entity = AddressEntity::class, parentColumn = "userId", entityColumn = "userId") val address: AddressParentEntity?,
    @Relation(entity = CompanyEntity::class, parentColumn = "userId", entityColumn = "userId") val company: CompanyEntity?
)