package br.com.finquest.features.home.ui.history

import br.com.finquest.core.model.data.GoalTransaction
import br.com.finquest.core.model.data.GoalWithSavedAmount

data class HistoryUiState(
    val isLoading: Boolean = false,
    val goals: List<GoalWithSavedAmount> = emptyList(),
    val transactions: List<GoalTransaction>? = null,
    val filterName: String? = null,
    val error: Throwable? = null
)