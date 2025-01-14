package br.com.finquest.navigation

import androidx.annotation.DrawableRes
import br.com.finquest.core.ui.R

sealed class NavigationItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    data object Home : NavigationItem(
        route = "home",
        title = "Inicio",
        icon = R.drawable.ic_home_fill
    )

    data object Add : NavigationItem(
        route = "add",
        title = "Meta",
        icon = R.drawable.ic_plus
    )

    data object History : NavigationItem(
        route = "history",
        title = "Histórico",
        icon = R.drawable.ic_money
    )
}