package br.com.finquest.features.home.ui.edit

import br.com.finquest.core.common.enums.BottomSheetType
import br.com.finquest.core.model.data.Goal

data class EditGoalUiState(
    val isLoading: Boolean = false,
    val name: String = "",
    val balance: String = "",
    val currentBalance: String = "",
    val deadline: String = "",
    val goal: Goal? = null,
    val openAddBalance: Boolean = false,
    val openDateDialog: Boolean = false,
    val bottomSheetType: BottomSheetType = BottomSheetType.BALANCE,
    val setAmountError: Boolean = false,
    val error: Throwable? = null
)