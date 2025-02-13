package br.com.finquest.features.home.ui.addamount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.finquest.core.common.util.toCents
import br.com.finquest.core.domain.InsertTransactionUseCase
import br.com.finquest.core.model.data.GoalTransaction
import br.com.finquest.core.utils.BalanceFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddAmountViewModel(
    private val insertTransactionUseCase: InsertTransactionUseCase
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

    fun insertTransaction(goalId: Int) {
        viewModelScope.launch {
            insertTransactionUseCase.invoke(
                transaction = GoalTransaction(
                    goalId = goalId,
                    amount = uiState.value.balance.toCents() ?: 0
                )
            )
        }
    }
}