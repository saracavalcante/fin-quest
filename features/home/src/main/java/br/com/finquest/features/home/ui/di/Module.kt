package br.com.finquest.features.home.ui.di

import br.com.finquest.features.home.ui.goals.GoalsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { GoalsViewModel() }
}