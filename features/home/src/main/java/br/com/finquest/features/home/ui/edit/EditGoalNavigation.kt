package br.com.finquest.features.home.ui.edit

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.finquest.core.model.data.Goal
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

private const val GOAL_ARG = "goal"
internal const val EDIT_GOAL_ROUTE = "edit_goal/{$GOAL_ARG}"

fun NavController.navigateToEditGoal(goal: Goal) {
    val gson = Gson().toJson(goal)
    navigate(EDIT_GOAL_ROUTE.replace("{$GOAL_ARG}", gson))
}

fun NavGraphBuilder.editGoalScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = EDIT_GOAL_ROUTE,
        arguments = listOf(navArgument(GOAL_ARG) { type = NavType.StringType })
    ) { backStackEntry ->
        val json = backStackEntry.arguments?.getString(GOAL_ARG) ?: ""
        val goal = Gson().fromJson(json, Goal::class.java)

        EditGoalScreen(
            goal = goal,
            viewModel = koinViewModel(),
            onBackClick = onBackClick
        )
    }
}
