package br.com.finquest.features.home.ui.goals

import br.com.finquest.core.common.enums.GoalEnum
import br.com.finquest.core.model.data.GoalWithSavedAmount

data class GoalsUiState(
    val goals: List<GoalWithSavedAmount> = listOf(),
    val isLoading: Boolean = false,
    val filter: GoalEnum = GoalEnum.IN_PROGRESS,
    val goalToDelete: Int? = null,
    val showDeleteDialog: Boolean = false,
    val error: Throwable? = null
)