package br.com.finquest.core.common.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.finquest.core.domain.ThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val themeUseCase: ThemeUseCase
) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode = _isDarkMode.asStateFlow()

    init {
        themeUseCase.themeFlow
            .onEach { _isDarkMode.value = it }
            .launchIn(viewModelScope)
    }

    fun toggleTheme(isDarkMode: Boolean) {
        viewModelScope.launch {
            themeUseCase.invoke(isDarkMode)
        }
    }
}