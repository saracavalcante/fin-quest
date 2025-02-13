package br.com.finquest.features.home.ui.details

import br.com.finquest.core.model.data.Goal

data class GoalDetailsUiState(
    val isLoading: Boolean = false,
    val goal: Goal? = null,
    val savedAmount: Long? = null,
    val showPauseDialog: Boolean = false,
    val error: Throwable? = null
)