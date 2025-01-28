package br.com.finquest.core.data.di

import br.com.finquest.core.data.repository.GoalRepository
import br.com.finquest.core.data.repository.GoalRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<GoalRepository> { GoalRepositoryImpl(get()) }
}