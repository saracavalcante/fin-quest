package br.com.finquest.features.home.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.finquest.core.domain.DeleteGoalUseCase
import br.com.finquest.core.domain.GetGoalUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GoalDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getGoalUseCase: GetGoalUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase,
) : ViewModel() {

    private val args = Args(savedStateHandle)

    private val _uiState = MutableStateFlow(GoalDetailsUiState())
    val uiState: StateFlow<GoalDetailsUiState> = _uiState

    fun showDeleteDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(showDeleteDialog = show)
    }

    fun showPauseDialog(show: Boolean) {
        _uiState.update { it.copy(showPauseDialog = show) }
    }

    fun getGoalById() {
        viewModelScope.launch {
            getGoalUseCase.invoke(args.id.toInt()).collect { goal ->
                _uiState.update { it.copy(goal = goal) }
            }
        }
    }

    fun deleteGoal() {
        viewModelScope.launch {
            deleteGoalUseCase.invoke(args.id.toInt())
        }
    }
}