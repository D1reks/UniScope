package com.example.uniscope.domain.model

data class Specialty(
    val id: String,
    val name: String,
    val code: String,
    val currentApplications: Int,
    val availablePlaces: Int,
    val requiredScore: Double
)
