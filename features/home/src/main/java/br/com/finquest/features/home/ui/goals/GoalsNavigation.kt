package br.com.finquest.features.home.ui.goals

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

const val GOALS_ROUTE = "goals"

fun NavGraphBuilder.goalsScreen(
    onGoalClick: (String) -> Unit
) {
    composable(route = GOALS_ROUTE) {
        GoalsScreen(
            viewModel = koinViewModel(),
            onGoalClick = onGoalClick
        )
    }
}