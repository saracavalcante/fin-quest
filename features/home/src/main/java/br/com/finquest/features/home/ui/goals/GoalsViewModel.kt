package br.com.finquest.features.home.ui.goals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.finquest.core.common.enums.GoalEnum
import br.com.finquest.core.domain.DeleteGoalUseCase
import br.com.finquest.core.domain.GetAllGoalsUseCase
import br.com.finquest.core.domain.PinGoalUseCase
import br.com.finquest.core.model.data.Goal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GoalsViewModel(
    private val goalsUseCase: GetAllGoalsUseCase,
    private val pinGoalUseCase: PinGoalUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GoalsUiState())
    val uiState: StateFlow<GoalsUiState> = _uiState

    private val _revealedIds = MutableStateFlow(listOf<Int>())
    val revealedIds: StateFlow<List<Int>> = _revealedIds

    fun setStatus(status: GoalEnum) {
        _uiState.update { it.copy(filter = status) }
    }

    fun togglePin(goal: Goal) {
        viewModelScope.launch {
            pinGoalUseCase.invoke(goal)
        }
    }

    fun showDeleteDialog(id: Int? = null, show: Boolean) {
        _uiState.update {
            it.copy(
                goalToDelete = id,
                showDeleteDialog = show
            )
        }
    }

    fun onItemExpanded(id: Int?) {
        if (_revealedIds.value.contains(id)) return
        _revealedIds.value = _revealedIds.value.toMutableList().also { list ->
            if (id != null) {
                list.add(id)
            }
        }
    }

    fun onItemCollapsed(id: Int?) {
        if (!_revealedIds.value.contains(id)) return
        _revealedIds.value = _revealedIds.value.toMutableList().also { list ->
            list.remove(id)
        }
    }

    fun getGoals() {
        viewModelScope.launch {
            goalsUseCase.invoke().collect { goals ->
                _uiState.update { it.copy(goals = goals) }
            }
        }
    }

    fun deleteGoal() {
        viewModelScope.launch {
            deleteGoalUseCase.invoke(
                id = uiState.value.goalToDelete ?: 0
            )
            getGoals()
        }
    }
}