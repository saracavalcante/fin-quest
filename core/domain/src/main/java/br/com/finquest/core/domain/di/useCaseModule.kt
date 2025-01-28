package br.com.finquest.core.domain.di

import br.com.finquest.core.domain.AddGoalUseCase
import br.com.finquest.core.domain.GetAllGoalsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { AddGoalUseCase(get()) }
    single { GetAllGoalsUseCase(get()) }
}