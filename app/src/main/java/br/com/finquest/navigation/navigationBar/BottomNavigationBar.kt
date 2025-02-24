package br.com.finquest.navigation.navigationBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.finquest.core.theme.FontFamily

@Composable
fun BottomNavigationBar(
    navController: NavController,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            content()
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val destinations = listOf(
        NavigationItem.Home,
        NavigationItem.Add,
        NavigationItem.History
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    val showNavigationBar by remember(currentRoute) {
        mutableStateOf(destinations.map { it.route }.contains(currentRoute))
    }

    AnimatedVisibility(
        visible = showNavigationBar
    ) {
        NavigationBar(
            containerColor = colorScheme.background
        ) {
            destinations.forEach { item ->
                NavItem(
                    screen = item,
                    selected = item.route == currentRoute
                ) {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.NavItem(
    screen: NavigationItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationBarItem(
        modifier = Modifier.align(Alignment.CenterVertically),
        selected = selected,
        label = {
            Text(
                text = screen.title,
                fontFamily = FontFamily,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        icon = {
            Icon(
                modifier = Modifier.size(26.dp),
                painter = painterResource(id = screen.icon),
                contentDescription = null
            )
        },
        onClick = onClick,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = colorScheme.primary,
            unselectedIconColor = colorScheme.onSurfaceVariant,
            selectedTextColor = colorScheme.primary,
            unselectedTextColor = colorScheme.onSurfaceVariant,
            indicatorColor = Color.Transparent
        )
    )
}