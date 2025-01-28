package br.com.finquest.features.home.ui.addgoal

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.finquest.core.common.enums.GoalEnum
import br.com.finquest.core.common.util.toCents
import br.com.finquest.core.domain.AddGoalUseCase
import br.com.finquest.core.model.data.Goal
import br.com.finquest.features.home.ui.addgoal.AddGoalUiState.Companion.BottomSheetType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class AddGoalViewModel(
    private val addGoalUseCase: AddGoalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddGoalUiState())
    val uiState: StateFlow<AddGoalUiState> = _uiState

    fun setName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun setBalance(balance: String) {
        val numericValue = if (uiState.value.bottomSheetType == BottomSheetType.BALANCE) {
            uiState.value.balance.replace("\\D".toRegex(), "")
        } else {
            uiState.value.currentBalance.replace("\\D".toRegex(), "")
        }

        val updatedValue = when (balance) {
            "DEL" -> {
                if (numericValue.isNotEmpty()) {
                    numericValue.dropLast(1)
                } else ""
            }

            else -> {
                numericValue + balance
            }
        }

        val formattedValue = if (updatedValue.isNotEmpty()) {
            val parsedValue = updatedValue.toLong()
            String.format(
                Locale("pt", "BR"),
                "%,.2f",
                parsedValue / 100.0
            ).replace('.', ',')
        } else "R$ 0,00"

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

    fun setDate(date: String) {
        _uiState.update { it.copy(date = date) }
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

    fun addGoal() {
        viewModelScope.launch {
            addGoalUseCase.invoke(
                Goal(
                    name = uiState.value.name,
                    icon = uiState.value.icon,
                    color = uiState.value.color.toArgb(),
                    targetAmount = uiState.value.balance.toCents(),
                    savedAmount = uiState.value.currentBalance.toCents(),
                    deadline = uiState.value.date,
                    status = GoalEnum.IN_PROGRESS.value
                )
            )
        }
    }
}