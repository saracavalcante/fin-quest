package br.com.finquest.features.home.ui.edit

import androidx.lifecycle.ViewModel
import br.com.finquest.core.common.util.toMoneyString
import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.utils.BalanceFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditGoalViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EditGoalUiState())
    val uiState = _uiState.asStateFlow()

    private val formatInput = BalanceFormatter()

    fun setName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun setBalance(balance: String) {
        val formattedValue = formatInput.formatInput(uiState.value.balance, balance)
        _uiState.update {
            it.copy(
                balance = formattedValue,
                setAmountError = false
            )
        }
    }

    fun setAmountError() {
        _uiState.update { it.copy(setAmountError = true) }
    }

    fun setDeadline(deadline: String) {
        _uiState.update { it.copy(deadline = deadline) }
    }

    fun setGoal(goal: Goal) {
        _uiState.update {
            it.copy(
                name = goal.name,
                balance = goal.targetAmount.toMoneyString()
            )
        }
    }

    fun openBottomSheet(open: Boolean) {
        _uiState.update {
            it.copy(openAddBalance = open)
        }
    }

    fun openDateDialog(open: Boolean) {
        _uiState.update { it.copy(openDateDialog = open) }
    }

    fun delete(balance: String) {
        _uiState.update {
            it.copy(balance = balance.dropLast(1))
        }
    }
}