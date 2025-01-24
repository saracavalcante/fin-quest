package br.com.finquest.navigation.navigationBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import br.com.finquest.features.home.ui.addgoal.ADD_GOAL_ROUTE
import br.com.finquest.features.home.ui.goals.GOALS_ROUTE
import br.com.finquest.features.home.ui.history.HISTORY_ROUTE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class BottomNavigationState(private val navController: NavController) {
    private val routes = listOf(
        GOALS_ROUTE,
        ADD_GOAL_ROUTE,
        HISTORY_ROUTE
    )

    fun openRoute(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun isRouteSelected(route: String): Flow<Boolean> {
        return navController.currentBackStack.map { backStack ->
            backStack
                .map { it.destination.route }
                .lastOrNull { routes.contains(it) }
                .let { it == route }
        }
    }
}

@Composable
internal fun rememberBottomNavigationState(navController: NavController): BottomNavigationState {
    return remember(navController) {
        BottomNavigationState(navController)
    }
}