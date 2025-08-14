package com.example.uniscope.presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.uniscope.domain.model.MainState
import com.example.uniscope.domain.model.Specialty
import com.example.uniscope.presentation.feature.main.MainIntent
import com.example.uniscope.presentation.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = { AppBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.sendIntent(MainIntent.RefreshData) }) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }
    ) { padding ->
        when (val currentState = state) {
            is MainState.Idle -> IdleScreen(padding)
            is MainState.Loading -> LoadingScreen(padding)
            is MainState.Content -> ContentScreen(
                padding = padding,
                state = currentState,
                onPreferencesChanged = { city, score ->
                    viewModel.sendIntent(MainIntent.SetUserPreferences(city, score))
                }
            )
            is MainState.Error -> ErrorScreen(padding, currentState.message)
        }
    }
}

@Composable
private fun ContentScreen(
    padding: PaddingValues,
    state: MainState.Content,
    onPreferencesChanged: (String, Double) -> Unit
) {
    Column(modifier = Modifier.padding(padding)) {
        UserPreferencesForm(
            currentCity = state.userPreferences.city,
            currentScore = state.userPreferences.averageScore,
            onApply = onPreferencesChanged,
            padding = padding
        )

        LazyColumn {
            items(state.filteredSpecialties) { specialty ->
                SpecialtyCard(specialty = specialty)
            }
        }
    }
}

@Composable
private fun SpecialtyCard(specialty: Specialty) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = specialty.name, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Код: ${specialty.code}")
            Text(text = "Требуемый балл: ${specialty.requiredScore}")
            Text(text = "Подано заявок: ${specialty.currentApplications}/${specialty.availablePlaces}")
        }
    }
}

@Composable
fun AppBar() {
    TODO("Not yet implemented")
}
