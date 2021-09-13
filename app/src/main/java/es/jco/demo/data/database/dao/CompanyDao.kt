package es.jco.demo.data.database.dao

import androidx.room.*
import es.jco.demo.data.database.entity.CompanyEntity

@Dao
interface CompanyDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(company: CompanyEntity): Long

    @Transaction
    @Query("SELECT * FROM CompanyEntity WHERE companyId = :companyId")
    suspend fun getCompany(companyId: Long): CompanyEntity?
}