package br.com.finquest.features.home.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

private const val GOAL_ID = "goalId"
internal const val GOAL_DETAIL_ROUTE = "goal_detail/{$GOAL_ID}"

internal data class Args(val id: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        id = checkNotNull(savedStateHandle[GOAL_ID])
    )
}

fun NavController.navigateToDetails(id: String) {
    navigate(GOAL_DETAIL_ROUTE.replace("{$GOAL_ID}", id))
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