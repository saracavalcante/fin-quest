package br.com.finquest.features.home.ui.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HISTORY_ROUTE = "history_route"

fun NavController.navigateToHistory() {
    navigate(HISTORY_ROUTE)
}

fun NavGraphBuilder.historyScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = HISTORY_ROUTE
    ) {
        HistoryScreen(
            onBackClick = onBackClick
        )
    }
}