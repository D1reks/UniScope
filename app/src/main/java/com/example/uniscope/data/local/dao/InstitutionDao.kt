package com.example.uniscope.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.uniscope.domain.model.EducationalInstitution
import com.example.uniscope.domain.model.Specialty
import kotlinx.coroutines.flow.Flow
import com.example.uniscope.data.local.entities.SpecialtyEntity
import com.example.uniscope.data.local.entities.InstitutionEntity
@Dao
interface  InstitutionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInstitutions(institutions: List<InstitutionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecialties(specialties: List<SpecialtyEntity>)

    @Query("SELECT * FROM institutions")
    fun getAllInstitutions(): Flow<List<InstitutionEntity>>

    @Query("SELECT * FROM institutions WHERE city = :city")
    fun getInstitutionsByCity(city: String): Flow<List<InstitutionEntity>>

    @Query("SELECT * FROM specialties WHERE institutionId = :institutionId")
    fun getSpecialtiesByInstitution(institutionId: String): Flow<List<SpecialtyEntity>>

    @Query("""
        SELECT specialties.* FROM specialties 
        INNER JOIN institutions ON specialties.institutionId = institutions.id
        WHERE institutions.city = :city AND specialties.requiredScore <= :userScore
        ORDER BY specialties.requiredScore DESC
    """)
    fun getSpecialtiesByCityAndScore(city: String, userScore: Double): Flow<List<SpecialtyEntity>>

    @Query("DELETE FROM institutions")
    suspend fun clearInstitutions()

    @Query("DELETE FROM specialties")
    suspend fun clearSpecialties()

    @Transaction
    suspend fun insertInstitutionWithSpecialties(
        institution: EducationalInstitution,
        specialties: List<Specialty>
    ) {
        insertInstitutions(listOf(InstitutionEntity.fromDomain(institution)))
        insertSpecialties(specialties.map { SpecialtyEntity.fromDomain(it, institution.id) })
    }
}