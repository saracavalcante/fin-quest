package br.com.finquest.core.data.repository

import br.com.finquest.core.data.ThemePreferences
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    val themeFlow: Flow<Boolean>
    suspend fun saveDarkMode(isDarkMode: Boolean)
}

class ThemeRepositoryImpl(
    private val themePreferences: ThemePreferences
): ThemeRepository {

    override val themeFlow: Flow<Boolean> = themePreferences.darkModeFlow

    override suspend fun saveDarkMode(isDarkMode: Boolean) {
        return themePreferences.saveDarkMode(isDarkMode)
    }
}