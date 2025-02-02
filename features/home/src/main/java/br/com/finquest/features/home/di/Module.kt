package br.com.finquest.features.home.di

import br.com.finquest.features.home.ui.addgoal.AddGoalViewModel
import br.com.finquest.features.home.ui.details.GoalDetailsViewModel
import br.com.finquest.features.home.ui.edit.EditGoalViewModel
import br.com.finquest.features.home.ui.goals.GoalsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { GoalsViewModel(get(), get()) }
    viewModel { AddGoalViewModel(get()) }
    viewModel { GoalDetailsViewModel(get(), get(), get()) }
    viewModel { EditGoalViewModel() }
}