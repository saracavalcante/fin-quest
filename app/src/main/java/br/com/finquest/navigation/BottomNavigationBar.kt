package br.com.finquest.navigation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.features.home.ui.goal.GoalScreen
import br.com.finquest.features.home.ui.goals.GoalsScreen
import br.com.finquest.features.home.ui.history.HistoryScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainScreenNavigation(navController = navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        NavigationItem.Home,
        NavigationItem.Add,
        NavigationItem.History
    )

    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Color(0xFFF7F7F7)
    ) {
        screens.forEachIndexed { index, navigationItem ->
            NavItem(
                screen = navigationItem,
                selected = selectedItemIndex == index
            ) {
                selectedItemIndex = index
                navController.navigate(navigationItem.route) {
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
        colors = NavigationBarItemColors(
            selectedIndicatorColor = Color(0xFFF7F7F7),
            selectedTextColor = Color(0xFF3831DB),
            unselectedTextColor = Color(0xFFBCBCBC),
            selectedIconColor = Color(0xFF3831DB),
            unselectedIconColor = Color(0xFFBCBCBC),
            disabledIconColor = Color(0xFFBCBCBC),
            disabledTextColor = Color(0xFFBCBCBC)
        )
    )
}

@Composable
fun MainScreenNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(NavigationItem.Home.route) {
            GoalsScreen(koinViewModel())
        }
        composable(NavigationItem.Add.route) {
            GoalScreen()
        }
        composable(NavigationItem.History.route) {
            HistoryScreen()
        }
    }
}