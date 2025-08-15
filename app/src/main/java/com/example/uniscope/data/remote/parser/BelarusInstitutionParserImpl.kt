package com.example.uniscope.data.remote.parser

import com.example.uniscope.domain.model.EducationalInstitution
import com.example.uniscope.domain.model.Specialty
import com.example.uniscope.domain.repository.InstitutionParser
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BelarusInstitutionParserImpl @Inject constructor(
    private val jsoup: JsoupWrapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : InstitutionParser {

    override suspend fun parseInstitutions(): List<EducationalInstitution> = withContext(dispatcher) {
        TODO()
    }

    override suspend fun parseSpecialties(institution: EducationalInstitution): List<Specialty> = withContext(dispatcher) {
       TODO()
    }
}