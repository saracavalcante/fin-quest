package br.com.finquest.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.finquest.core.database.dao.GoalDao
import br.com.finquest.core.database.model.GoalEntity

@Database(entities = [GoalEntity::class], version = 1, exportSchema = false)
abstract class FinQuestDataBase : RoomDatabase() {

    abstract fun goalDao(): GoalDao
}