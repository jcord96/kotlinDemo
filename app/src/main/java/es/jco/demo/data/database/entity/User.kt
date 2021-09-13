package es.jco.demo.data.database.entity

import androidx.room.*

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