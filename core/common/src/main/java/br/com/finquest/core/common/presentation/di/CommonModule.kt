package br.com.finquest.core.common.presentation.di

import br.com.finquest.core.common.presentation.ThemeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    viewModel { ThemeViewModel(get()) }
}