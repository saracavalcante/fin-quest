package br.com.finquest.features.home.ui.addgoal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AddGoalViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddGoalUiState())
    val uiState: StateFlow<AddGoalUiState> = _uiState

    fun setName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun setBalance(balance: String) {
        _uiState.update { it.copy(balance = balance) }
    }

    fun setCurrentBalance(currentBalance: String) {
        _uiState.update { it.copy(currentBalance = currentBalance) }
    }

    fun setDate(date: String) {
        _uiState.update { it.copy(date = date) }
    }

    fun setIcon(icon: String) {
        _uiState.update { it.copy(icon = icon) }
    }

    fun showDialog(show: Boolean) {
        _uiState.update { it.copy(showDialog = show) }
    }

    fun openCustomization(open: Boolean) {
        _uiState.update { it.copy(openCustomization = open) }
    }
}