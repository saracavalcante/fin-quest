package br.com.finquest.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.finquest.features.home.ui.addamount.addAmountScreen
import br.com.finquest.features.home.ui.addamount.navigateToAddAmount
import br.com.finquest.features.home.ui.addgoal.addGoalScreen
import br.com.finquest.features.home.ui.details.goalDetailsScreen
import br.com.finquest.features.home.ui.details.navigateToDetails
import br.com.finquest.features.home.ui.edit.editGoalScreen
import br.com.finquest.features.home.ui.edit.navigateToEditGoal
import br.com.finquest.features.home.ui.goals.GOALS_ROUTE
import br.com.finquest.features.home.ui.goals.goalsScreen
import br.com.finquest.features.home.ui.goals.navigateToGoals
import br.com.finquest.features.home.ui.history.historyScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = GOALS_ROUTE
    ) {
        goalsScreen(
            onGoalClick = { goalId ->
                navController.navigateToDetails(goalId.toString())
            }
        )
        addGoalScreen(
            onClick = { navController.navigateToGoals() }
        )
        historyScreen()
        goalDetailsScreen(
            onBackClick = navController::popBackStack,
            onEditClick = {
                navController.navigateToEditGoal(it)
            },
            onAddClick = {
                navController.navigateToAddAmount(it)
            }
        )
        editGoalScreen(
            onBackClick = navController::popBackStack
        )
        addAmountScreen(
            onBackClick = navController::popBackStack
        )
    }
}