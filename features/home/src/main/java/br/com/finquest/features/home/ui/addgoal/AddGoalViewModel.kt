package br.com.finquest.features.home.ui.addgoal

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.finquest.core.common.enums.BottomSheetType
import br.com.finquest.core.common.enums.GoalEnum
import br.com.finquest.core.common.util.toCents
import br.com.finquest.core.common.util.toLocalDate
import br.com.finquest.core.data.worker.scheduleGoalReminder
import br.com.finquest.core.domain.AddGoalUseCase
import br.com.finquest.core.domain.InsertTransactionUseCase
import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.model.data.GoalTransaction
import br.com.finquest.core.utils.BalanceFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddGoalViewModel(
    private val application: Application,
    private val addGoalUseCase: AddGoalUseCase,
    private val insertTransactionUseCase: InsertTransactionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddGoalUiState())
    val uiState: StateFlow<AddGoalUiState> = _uiState

    private val formatInput = BalanceFormatter()

    fun setName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun setBalance(balance: String) {
        val currentValue = if (uiState.value.bottomSheetType == BottomSheetType.BALANCE) {
            uiState.value.balance
        } else {
            uiState.value.currentBalance
        }

        val formattedValue = formatInput.formatInput(currentValue, balance)

        _uiState.update {
            if (uiState.value.bottomSheetType == BottomSheetType.BALANCE) {
                it.copy(balance = formattedValue)
            } else {
                it.copy(currentBalance = formattedValue)
            }
        }
    }

    fun setCurrentBalance(currentBalance: String) {
        _uiState.update { it.copy(currentBalance = currentBalance) }
    }

    fun setDeadline(date: String) {
        _uiState.update { it.copy(deadline = date) }
    }

    fun openDateDialog(open: Boolean) {
        _uiState.update { it.copy(openDateDialog = open) }
    }

    fun setIcon(icon: Int) {
        _uiState.update { it.copy(icon = icon) }
    }

    fun cancelCustomization() {
        _uiState.update {
            it.copy(
                color = Color.White,
                icon = 0
            )
        }
    }

    fun setColor(color: Color) {
        _uiState.update { it.copy(color = color) }
    }

    fun openCustomization(open: Boolean) {
        _uiState.update { it.copy(openCustomization = open) }
    }

    fun openBottomSheet(
        type: BottomSheetType = BottomSheetType.NONE,
        open: Boolean
    ) {
        _uiState.update {
            it.copy(
                bottomSheetType = type,
                openAddBalance = open
            )
        }
    }

    fun delete(balance: String) {
        _uiState.update {
            if (balance.isNotEmpty() && it.bottomSheetType == BottomSheetType.BALANCE) {
                it.copy(balance = balance.dropLast(1))
            } else {
                it.copy(currentBalance = balance.dropLast(1))
            }
        }
    }

    private fun scheduleReminder(name: String, deadline: String) {
        scheduleGoalReminder(application, name, deadline.toLocalDate())
    }

    fun addGoal() {
        viewModelScope.launch {
            val goal = Goal(
                name = uiState.value.name,
                icon = uiState.value.icon,
                color = uiState.value.color.toArgb(),
                targetAmount = uiState.value.balance.toCents(),
                deadline = uiState.value.deadline,
                status = GoalEnum.IN_PROGRESS.value
            )

            val goalId = addGoalUseCase.invoke(goal)
            val savedAmount = uiState.value.currentBalance.toCents()

            if (savedAmount != null && savedAmount > 0) {
                insertTransactionUseCase.invoke(
                    GoalTransaction(
                        goalId = goalId.toInt(),
                        amount = savedAmount
                    )
                )
            }
            scheduleReminder(uiState.value.name, uiState.value.deadline)
        }
    }
}