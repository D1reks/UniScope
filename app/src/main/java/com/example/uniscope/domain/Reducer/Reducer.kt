package com.example.uniscope.domain.Reducer

interface Reducer<State, Intent> {
    fun reduce(currentState: State, intent: Intent): State
}