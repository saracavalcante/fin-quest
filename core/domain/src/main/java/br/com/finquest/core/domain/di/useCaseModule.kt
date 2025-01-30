package br.com.finquest.core.domain.di

import br.com.finquest.core.domain.AddGoalUseCase
import br.com.finquest.core.domain.DeleteGoalUseCase
import br.com.finquest.core.domain.GetAllGoalsUseCase
import br.com.finquest.core.domain.GetGoalUseCase
import br.com.finquest.core.domain.PinGoalUseCase
import br.com.finquest.core.domain.UpdateGoalUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { AddGoalUseCase(get()) }
    single { GetAllGoalsUseCase(get()) }
    single { GetGoalUseCase(get()) }
    single { PinGoalUseCase(get()) }
    single { DeleteGoalUseCase(get()) }
    single { UpdateGoalUseCase(get()) }
}