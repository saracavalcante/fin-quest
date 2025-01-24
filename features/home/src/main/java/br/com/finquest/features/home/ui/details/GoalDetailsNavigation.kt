package br.com.finquest.features.home.ui.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

internal const val GOAL_DETAIL_ROUTE = "goal_detail"

fun NavController.navigateToDetails() {
    navigate(GOAL_DETAIL_ROUTE)
}

fun NavGraphBuilder.goalDetailsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = GOAL_DETAIL_ROUTE
    ) {
        GoalDetailsScreen(
            viewModel = koinViewModel(),
            onBackClick = onBackClick
        )
    }
}