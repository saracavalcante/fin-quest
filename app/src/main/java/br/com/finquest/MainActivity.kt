package br.com.finquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import br.com.finquest.core.theme.FinQuestTheme
import br.com.finquest.navigation.AppNavHost
import br.com.finquest.navigation.navigationBar.BottomNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinQuestTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    BottomNavigationBar(
        navController = navController
    ) {
        AppNavHost(navController)
    }
}