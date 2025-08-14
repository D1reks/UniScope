package com.example.uniscope.domain.Reducer

import com.example.uniscope.domain.model.MainState
import com.example.uniscope.presentation.feature.main.MainIntent

class MainReducerImpl : Reducer<MainState, MainIntent> {
    override fun reduce(currentState: MainState, intent: MainIntent): MainState {
        return when (intent) {
            is MainIntent.SetUserPreferences -> {
                TODO()
            }
            is MainIntent.RefreshData -> {
                TODO()
            }
            is MainIntent.FilterByScore -> TODO()
            MainIntent.LoadCachedData -> TODO()
        }
    }
}