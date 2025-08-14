package com.example.uniscope.presentation.feature.main

sealed class MainIntent {
    data class SetUserPreferences(val city: String, val averageScore: Double) : MainIntent()
    object RefreshData : MainIntent()
    object LoadCachedData : MainIntent()
    data class FilterByScore(val minScore: Double, val maxScore: Double) : MainIntent()
}