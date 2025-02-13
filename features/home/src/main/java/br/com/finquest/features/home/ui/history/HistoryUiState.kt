package br.com.finquest.features.home.ui.history

import br.com.finquest.core.model.data.GoalWithSavedAmount

data class HistoryUiState(
    val isLoading: Boolean = false,
    val goals: List<GoalWithSavedAmount> = emptyList(),
    val error: Throwable? = null
)