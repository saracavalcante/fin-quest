package br.com.finquest.core.data.di

import br.com.finquest.core.data.ThemePreferences
import br.com.finquest.core.data.repository.GoalRepository
import br.com.finquest.core.data.repository.GoalRepositoryImpl
import br.com.finquest.core.data.repository.ThemeRepository
import br.com.finquest.core.data.repository.ThemeRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single { ThemePreferences(get()) }
    single<GoalRepository> { GoalRepositoryImpl(get()) }
    single<ThemeRepository> { ThemeRepositoryImpl(get()) }
}