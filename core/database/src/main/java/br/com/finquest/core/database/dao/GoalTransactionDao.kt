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

    @Query("SELECT * FROM goal_transactions WHERE goalId = :goalId ORDER BY dateAdded DESC")
    fun getTransactionsForGoal(goalId: String): Flow<List<GoalTransactionEntity>>

    @Query("SELECT SUM(amount) FROM goal_transactions WHERE goalId = :goalId")
    suspend fun getSavedAmount(goalId: Int): Long?
}