package br.com.finquest.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.finquest.core.database.dao.GoalDao
import br.com.finquest.core.database.dao.GoalTransactionDao
import br.com.finquest.core.database.model.GoalEntity
import br.com.finquest.core.database.model.GoalTransactionEntity
import br.com.finquest.core.database.utils.Converters

@Database(entities = [GoalEntity::class, GoalTransactionEntity::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FinQuestDataBase : RoomDatabase() {

    abstract fun goalDao(): GoalDao
    abstract fun goalTransactionDao(): GoalTransactionDao
}