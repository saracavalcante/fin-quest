package br.com.finquest.features.home.ui.goals

import br.com.finquest.core.common.enums.GoalEnum
import br.com.finquest.core.model.data.Goal

data class GoalsUiState(
    val goals: List<Goal> = listOf(),
    val isLoading: Boolean = false,
    val filter: GoalEnum = GoalEnum.IN_PROGRESS,
    val error: Throwable? = null
)