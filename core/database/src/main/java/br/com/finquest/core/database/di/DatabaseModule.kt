package br.com.finquest.core.database.di

import androidx.room.Room
import br.com.finquest.core.database.FinQuestDataBase
import br.com.finquest.core.database.datasource.GoalLocalDataSource
import br.com.finquest.core.database.datasource.GoalLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            FinQuestDataBase::class.java,
            "finquest_database"
        ).build()
    }

    factory { get<FinQuestDataBase>().goalDao() }

    single<GoalLocalDataSource> { GoalLocalDataSourceImpl(get()) }
}