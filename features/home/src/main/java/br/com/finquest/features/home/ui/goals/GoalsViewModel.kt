package br.com.finquest.features.home.ui.goals

import androidx.lifecycle.ViewModel
import br.com.finquest.core.model.data.Goal
import br.com.finquest.features.home.ui.goals.GoalsUiState.Companion.GoalEnum
import br.com.finquest.features.home.ui.goals.GoalsUiState.Companion.GoalEnum.IN_PROGRESS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GoalsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GoalsUiState())
    val uiState: StateFlow<GoalsUiState> = _uiState

    init {
        getGoals()
    }

    fun setStatus(status: GoalEnum) {
        _uiState.update { it.copy(filter = status) }
    }

    private fun getGoals() {
        val list = listOf(
            Goal(
                title = "Viagem",
                icon = null,
                amount = 2000,
                deadline = "25/01/2025",
                status = IN_PROGRESS.value
            ),
            Goal(
                title = "Carro",
                icon = null,
                amount = 2000,
                deadline = "25/01/2025",
                status = IN_PROGRESS.value
            ),
            Goal(
                title = "Carro",
                icon = null,
                amount = 2000,
                deadline = "25/01/2025",
                status = IN_PROGRESS.value
            ),
            Goal(
                title = "Carro",
                icon = null,
                amount = 2000,
                deadline = "25/01/2025",
                status = IN_PROGRESS.value
            ),
            Goal(
                title = "Carro",
                icon = null,
                amount = 2000,
                deadline = "25/01/2025",
                status = IN_PROGRESS.value
            ),
            Goal(
                title = "Carro",
                icon = null,
                amount = 2000,
                deadline = "25/01/2025",
                status = IN_PROGRESS.value
            )
        )

        _uiState.update { it.copy(goals = list) }
    }
}