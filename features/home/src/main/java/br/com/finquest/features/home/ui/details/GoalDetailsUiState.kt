package br.com.finquest.features.home.ui.details

import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.model.data.MonthlyContribution

data class GoalDetailsUiState(
    val isLoading: Boolean = false,
    val goal: Goal? = null,
    val showPauseDialog: Boolean = false,
    val error: Throwable? = null
)

fun getCompleteMonthlyData(contributions: List<MonthlyContribution>): List<MonthlyContribution> {
    val allMonths = listOf(
        "Jul", "Aug", "Set", "Out", "Nov", "Dez"
    )

    return allMonths.map { month ->
        contributions.find { it.month == month } ?: MonthlyContribution(month, 0f)
    }
}