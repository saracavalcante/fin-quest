package br.com.finquest.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.finquest.core.database.model.GoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goalEntity: GoalEntity)

    @Query("SELECT * FROM goals")
    fun getAllGoals(): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goals WHERE id = :id")
    fun getGoalById(id: Int): Flow<GoalEntity>

    @Update
    fun updateGoal(goalEntity: GoalEntity)

    @Delete
    fun deleteGoal(goalEntity: GoalEntity)
}