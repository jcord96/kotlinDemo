package es.jco.demo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Company (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?,
    val catchPhrase: String?,
    val bs: String?
)