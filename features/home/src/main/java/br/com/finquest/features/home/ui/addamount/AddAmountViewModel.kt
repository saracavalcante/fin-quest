package br.com.finquest.features.home.ui.addamount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.finquest.core.common.util.toCents
import br.com.finquest.core.domain.UpdateGoalUseCase
import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.utils.BalanceFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddAmountViewModel(
    private val updateGoalUseCase: UpdateGoalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddAmountUiState())
    val uiState: StateFlow<AddAmountUiState> = _uiState.asStateFlow()

    private val formatInput = BalanceFormatter()

    fun delete(balance: String) {
        _uiState.update {
            it.copy(balance = balance.dropLast(1))
        }
    }

    fun setBalance(balance: String) {
        val formattedValue = formatInput.formatInput(uiState.value.balance, balance)
        _uiState.update {
            it.copy(
                balance = formattedValue
            )
        }
    }

    fun updateGoal(goal: Goal?) {
        val updatedGoal = goal?.copy(
            savedAmount = goal.savedAmount?.plus(
                uiState.value.balance.toCents()
            )
        )
        viewModelScope.launch {
            updateGoalUseCase.invoke(
                goal = updatedGoal
            )
        }
    }
}