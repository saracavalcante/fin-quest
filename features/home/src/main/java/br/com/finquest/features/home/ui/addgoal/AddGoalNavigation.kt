package br.com.finquest.features.home.ui.addgoal

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

const val ADD_GOAL_ROUTE = "add_goal"

fun NavController.navigateToEditGoal() {
    navigate(ADD_GOAL_ROUTE)
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