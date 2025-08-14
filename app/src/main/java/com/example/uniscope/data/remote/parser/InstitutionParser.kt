package com.example.uniscope.data.remote.parser

import com.example.uniscope.domain.model.EducationalInstitution
import com.example.uniscope.domain.model.Specialty

interface InstitutionParser {
    suspend fun parseInstitutions(): List<EducationalInstitution>
    suspend fun parseSpecialties(institution: EducationalInstitution): List<Specialty>
}