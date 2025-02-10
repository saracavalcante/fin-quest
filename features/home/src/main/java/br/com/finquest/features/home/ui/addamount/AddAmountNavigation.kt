package br.com.finquest.features.home.ui.addamount

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.finquest.core.model.data.Goal
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

private const val GOAL_ARG = "goal"
internal const val ADD_AMOUNT_ROUTE = "add_amount/{$GOAL_ARG}"

fun NavController.navigateToAddAmount(goal: Goal) {
    val gson = Gson().toJson(goal)
    navigate(ADD_AMOUNT_ROUTE.replace("{${GOAL_ARG}}", Uri.encode(gson)))
}

fun NavGraphBuilder.addAmountScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ADD_AMOUNT_ROUTE,
        arguments = listOf(navArgument(GOAL_ARG) { type = NavType.StringType })
    ) { backStackEntry ->
        val json = backStackEntry.arguments?.getString(GOAL_ARG) ?: ""
        val goal = Gson().fromJson(json, Goal::class.java)

        AddAmountScreen(
            goal = goal,
            viewModel = koinViewModel(),
            onBackClick = onBackClick
        )
    }
}