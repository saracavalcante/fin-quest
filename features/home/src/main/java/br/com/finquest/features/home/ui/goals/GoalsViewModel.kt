package br.com.finquest.features.home.ui.goals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.finquest.core.common.enums.GoalEnum
import br.com.finquest.core.domain.GetAllGoalsUseCase
import br.com.finquest.core.domain.PinGoalUseCase
import br.com.finquest.core.model.data.Goal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GoalsViewModel(
    private val goalsUseCase: GetAllGoalsUseCase,
    private val pinGoalUseCase: PinGoalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GoalsUiState())
    val uiState: StateFlow<GoalsUiState> = _uiState

    fun setStatus(status: GoalEnum) {
        _uiState.update { it.copy(filter = status) }
    }

    fun togglePin(goal: Goal) {
        viewModelScope.launch {
            pinGoalUseCase.invoke(goal)
        }
    }

    fun getGoals() {
        viewModelScope.launch {
            goalsUseCase.invoke().collect { goals ->
                _uiState.update { it.copy(goals = goals) }
            }
        }
    }
}