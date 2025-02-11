package br.com.finquest.features.home.ui.addgoal

import androidx.compose.ui.graphics.Color
import br.com.finquest.core.common.enums.BottomSheetType
import br.com.finquest.core.ui.R

data class AddGoalUiState(
    val isLoading: Boolean = false,
    val name: String = "",
    val balance: String = "",
    val currentBalance: String = "",
    val deadline: String = "",
    val icon: Int = R.drawable.ic_savings,
    val color: Color = Color(0xFFF5F5F5),
    val showDialog: Boolean = false,
    val openDateDialog: Boolean = false,
    val openCustomization: Boolean = false,
    val openAddBalance: Boolean = false,
    val bottomSheetType: BottomSheetType = BottomSheetType.NONE,
    val error: Throwable? = null
) {
    fun isEnabled(): Boolean {
        return name.isNotEmpty() &&
                balance.isNotEmpty()
    }
}

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