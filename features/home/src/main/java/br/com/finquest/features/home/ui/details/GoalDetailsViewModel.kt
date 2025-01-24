package br.com.finquest.features.home.ui.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GoalDetailsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GoalDetailsUiState())
    val uiState: StateFlow<GoalDetailsUiState> = _uiState

    fun showDeleteDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(showDeleteDialog = show)
    }

    fun showPauseDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(showPauseDialog = show)
    }
}