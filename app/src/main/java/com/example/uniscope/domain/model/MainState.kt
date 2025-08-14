package com.example.uniscope.domain.model

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Content(
        val institutions: List<EducationalInstitution>,
        val filteredSpecialties: List<Specialty>,
        val userPreferences: UserPreferences
    ) : MainState()
    data class Error(val message: String) : MainState()
}