package com.example.uniscope.domain.repository.Implementation

import com.example.uniscope.domain.repository.InstitutionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InstitutionRepositoryImpl @Inject constructor(
    private val parser: InstitutionParser,
    private val dao: InstitutionDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : InstitutionRepository {

    override fun getInstitutionsStream(): Flow<List<EducationalInstitution>> {
        return dao.getAllInstitutions()
    }

    override suspend fun refreshData() = withContext(dispatcher) {
        val institutions = parser.parseInstitutions()
        institutions.forEach { institution ->
            val specialties = parser.parseSpecialties(institution)
            dao.insertInstitutionWithSpecialties(institution, specialties)
        }
    }

    override fun getFilteredSpecialties(city: String, minScore: Double): Flow<List<Specialty>> {
        return dao.getSpecialtiesByCityAndScore(city, minScore)
    }
}