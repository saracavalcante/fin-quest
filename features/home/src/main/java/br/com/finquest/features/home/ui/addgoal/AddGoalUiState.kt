package br.com.finquest.features.home.ui.addgoal

import androidx.compose.ui.graphics.Color
import br.com.finquest.core.ui.R

data class AddGoalUiState(
    val isLoading: Boolean = false,
    val name: String = "",
    val balance: String = "",
    val currentBalance: String = "",
    val date: String = "",
    val icon: String = "",
    val showDialog: Boolean = false,
    val openCustomization: Boolean = false,
    val error: Throwable? = null
)

object GoalIcons {
    val icons = listOf(
        R.drawable.ic_car,
        R.drawable.ic_key,
        R.drawable.ic_pet,
        R.drawable.ic_clothes,
        R.drawable.ic_face,
        R.drawable.ic_flight,
        R.drawable.ic_heart,
        R.drawable.ic_rocket,
        R.drawable.ic_star,
    )
}

object GoalColors {
    val colors = listOf(
        Color(0xFFFFF1B7),
        Color(0xFFB2CDFA),
        Color(0xFFFFE0D6),
        Color(0xFFC7FFC7),
        Color(0xFFD6C8FF),
    )
}