package br.com.finquest.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.finquest.core.database.model.GoalTransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalTransactionDao {

    @Insert
    suspend fun insertTransaction(transaction: GoalTransactionEntity)

    @Query("""
        SELECT gt.*, g.name AS name
        FROM goal_transactions gt
        INNER JOIN goals g ON g.id = gt.goalId
        WHERE(:goalId IS NULL OR gt.goalId = :goalId)
        ORDER BY gt.dateAdded DESC
    """)
    fun getTransactionsForGoal(goalId: String): Flow<List<GoalTransactionEntity>>

    @Query(
        """
            SELECT gt.*, g.name AS name
            FROM goal_transactions gt
            INNER JOIN goals g ON g.id = gt.goalId
            ORDER BY gt.dateAdded DESC
        """
    )
    fun getAllTransactions(): Flow<List<GoalTransactionEntity>>

    @Query("SELECT SUM(amount) FROM goal_transactions WHERE goalId = :goalId")
    suspend fun getSavedAmount(goalId: Int): Long?
}