package br.com.finquest.features.home.ui.goals

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

const val GOALS_ROUTE = "goals"

fun NavController.navigateToGoals() {
    navigate(GOALS_ROUTE)
}

fun NavGraphBuilder.goalsScreen(
    onGoalClick: (Int?) -> Unit
) {
    composable(route = GOALS_ROUTE) {
        GoalsScreen(
            viewModel = koinViewModel(),
            onGoalClick = onGoalClick
        )
    }
}