package es.jco.demo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyEntity(
    @PrimaryKey(autoGenerate = true) var companyId: Long?,
    val name: String?,
    val catchPhrase: String?,
    val bs: String?,
    val userId: Long?
)