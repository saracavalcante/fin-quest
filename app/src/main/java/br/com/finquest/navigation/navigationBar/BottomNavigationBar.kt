package br.com.finquest.navigation.navigationBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
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
    val screens = listOf(
        NavigationItem.Home,
        NavigationItem.Add,
        NavigationItem.History
    )

    val state = rememberBottomNavigationState(navController)
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Color.White
    ) {
        screens.forEachIndexed { index, navigationItem ->
            NavItem(
                screen = navigationItem,
                selected = selectedItemIndex == index
            ) {
                selectedItemIndex = index
                state.openRoute(navigationItem.route)
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
            selectedIndicatorColor = Color.White,
            selectedTextColor = Color.Black,
            unselectedTextColor = Color(0xFFBCBCBC),
            selectedIconColor = Color.Black,
            unselectedIconColor = Color(0xFFBCBCBC),
            disabledIconColor = Color(0xFFBCBCBC),
            disabledTextColor = Color(0xFFBCBCBC)
        )
    )
}