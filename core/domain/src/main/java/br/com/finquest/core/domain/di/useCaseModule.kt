package br.com.finquest.core.domain.di

import br.com.finquest.core.domain.AddGoalUseCase
import br.com.finquest.core.domain.GetAllGoalsUseCase
import br.com.finquest.core.domain.GetGoalUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { AddGoalUseCase(get()) }
    single { GetAllGoalsUseCase(get()) }
    single { GetGoalUseCase(get()) }
}