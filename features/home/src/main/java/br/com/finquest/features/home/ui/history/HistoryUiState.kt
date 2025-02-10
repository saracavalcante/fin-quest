package br.com.finquest.features.home.ui.history

import br.com.finquest.core.model.data.Goal

data class HistoryUiState(
    val isLoading: Boolean = false,
    val goals: List<Goal> = emptyList(),
    val error: Throwable? = null
)