package com.example.uniscope.domain.model

data class EducationalInstitution(
    val id: String,
    val name: String,
    val type: InstitutionType, // COLLEGE or UNIVERSITY
    val city: String,
    val logoUrl: String?,
    val website: String,
    val specialties: List<Specialty>
)
