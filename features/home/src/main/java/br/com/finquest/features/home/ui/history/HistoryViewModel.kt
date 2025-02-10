package br.com.finquest.features.home.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.finquest.core.domain.GetAllGoalsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val getAllGoalsUseCase: GetAllGoalsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    fun getGoals() {
        viewModelScope.launch {
            getAllGoalsUseCase.invoke().collect { goals ->
                _uiState.update {
                    it.copy(goals = goals)
                }
            }
        }
    }
}