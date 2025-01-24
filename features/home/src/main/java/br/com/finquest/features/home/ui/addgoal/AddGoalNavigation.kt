package br.com.finquest.features.home.ui.addgoal

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

const val ADD_GOAL_ROUTE = "add_goal"

fun NavGraphBuilder.addGoalScreen(
    onBackClick: () -> Unit
) {
    composable(route = ADD_GOAL_ROUTE) {
        AddGoalScreen(
            viewModel = koinViewModel(),
            onBackClick = onBackClick
        )
    }
}