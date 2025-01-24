package br.com.finquest.navigation.navigationBar

import androidx.annotation.DrawableRes
import br.com.finquest.core.ui.R
import br.com.finquest.features.home.ui.addgoal.ADD_GOAL_ROUTE
import br.com.finquest.features.home.ui.goals.GOALS_ROUTE
import br.com.finquest.features.home.ui.history.HISTORY_ROUTE

sealed class NavigationItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    data object Home : NavigationItem(
        route = GOALS_ROUTE,
        title = "Inicio",
        icon = R.drawable.ic_home_fill
    )

    data object Add : NavigationItem(
        route = ADD_GOAL_ROUTE,
        title = "Meta",
        icon = R.drawable.ic_plus
    )

    data object History : NavigationItem(
        route = HISTORY_ROUTE,
        title = "Histórico",
        icon = R.drawable.ic_money
    )
}