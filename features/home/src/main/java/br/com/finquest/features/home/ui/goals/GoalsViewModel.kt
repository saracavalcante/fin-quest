package br.com.finquest.features.home.ui.goals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.finquest.core.common.enums.GoalEnum
import br.com.finquest.core.domain.GetAllGoalsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GoalsViewModel(
    private val useCase: GetAllGoalsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GoalsUiState())
    val uiState: StateFlow<GoalsUiState> = _uiState

    init {
        getGoals()
    }

    fun setStatus(status: GoalEnum) {
        _uiState.update { it.copy(filter = status) }
    }

    private fun getGoals() {
        viewModelScope.launch {
            useCase.invoke().collect { goals ->
                _uiState.update { it.copy(goals = goals) }
            }
        }
    }
}