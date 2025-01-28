package br.com.finquest.features.home.ui.addgoal

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.finquest.features.home.ui.goals.GOALS_ROUTE
import org.koin.androidx.compose.koinViewModel

const val ADD_GOAL_ROUTE = "add_goal"

fun NavController.navigateToGoals() {
    navigate(GOALS_ROUTE)
}

fun NavGraphBuilder.addGoalScreen(
    onClick: () -> Unit
) {
    composable(route = ADD_GOAL_ROUTE) {
        AddGoalScreen(
            viewModel = koinViewModel(),
            onBackClick = onClick
        )
    }
}