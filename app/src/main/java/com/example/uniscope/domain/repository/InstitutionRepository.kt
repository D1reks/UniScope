package com.example.uniscope.domain.repository

import com.example.uniscope.domain.model.EducationalInstitution
import com.example.uniscope.domain.model.Specialty
import kotlinx.coroutines.flow.Flow

interface InstitutionRepository {
    fun getInstitutionsStream(): Flow<List<EducationalInstitution>>
    suspend fun refreshData()
    fun getFilteredSpecialties(city: String, minScore: Double): Flow<List<Specialty>>
}