package com.example.uniscope.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uniscope.domain.model.MainState
import com.example.uniscope.domain.model.MainState.*
import com.example.uniscope.domain.repository.InstitutionRepository
import com.example.uniscope.presentation.feature.main.MainIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: InstitutionRepository
) : ViewModel() {

    private val reducer = MainReducer()
    private val _state = MutableStateFlow<MainState>(Idle)
    val state: StateFlow<MainState> = _state.asStateFlow()

    private val _intents = Channel<MainIntent>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            _intents.consumeAsFlow().collect { intent ->
                _state.value = reducer.reduce(_state.value, intent)
                processIntent(intent)
            }
        }

        sendIntent(MainIntent.LoadCachedData)
    }

    fun sendIntent(intent: MainIntent) {
        viewModelScope.launch {
            _intents.send(intent)
        }
    }

    private suspend fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.SetUserPreferences -> {
                TODO()
            }
            is MainIntent.RefreshData -> {
                try {
                    _state.value = Loading
                    repository.refreshData()
                    // Обновить состояние с новыми данными
                } catch (e: Exception) {
                    _state.value = Error(e.message ?: "Unknown error")
                }
            }
            is MainIntent.FilterByScore -> TODO()
            MainIntent.LoadCachedData -> TODO()
        }
    }
}