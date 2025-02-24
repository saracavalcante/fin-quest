package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class ThemeUseCase(
    private val repository: ThemeRepository
) {
    val themeFlow: Flow<Boolean> = repository.themeFlow

    suspend operator fun invoke(isDarkMode: Boolean) {
        repository.saveDarkMode(isDarkMode)
    }
}