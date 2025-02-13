package br.com.finquest.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.finquest.core.database.model.GoalEntity
import br.com.finquest.core.database.model.GoalWithSavedAmountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goalEntity: GoalEntity): Long

    @Query(
        """
        SELECT goals.*,
        (SELECT SUM(amount) FROM goal_transactions WHERE goal_transactions.goalId = goals.id) AS savedAmount
        FROM goals ORDER BY isPinned DESC, id ASC
    """
    )
    fun getAllGoals(): Flow<List<GoalWithSavedAmountEntity>>

    @Query(
        """
        SELECT goals.*,
        (SELECT COALESCE(SUM(amount), 0) FROM goal_transactions WHERE goalId = goals.id) AS savedAmount
        FROM goals
        WHERE goals.id = :id
    """)
    fun getGoalById(id: Int): Flow<GoalWithSavedAmountEntity>

    @Update
    suspend fun updateGoal(goalEntity: GoalEntity)

    @Query("DELETE FROM goals WHERE id = :id")
    suspend fun deleteGoal(id: Int)
}