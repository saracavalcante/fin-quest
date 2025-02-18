package br.com.finquest.core.data.repository

import br.com.finquest.core.database.datasource.GoalLocalDataSource
import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.model.data.GoalTransaction
import br.com.finquest.core.model.data.GoalWithSavedAmount
import kotlinx.coroutines.flow.Flow

interface GoalRepository {
    suspend fun getAllGoals(): Flow<List<GoalWithSavedAmount>>
    suspend fun insertGoal(goal: Goal): Long
    suspend fun getGoalById(id: Int): Flow<GoalWithSavedAmount>
    suspend fun updatePinStatus(goal: Goal)
    suspend fun updateGoal(goal: Goal?)
    suspend fun deleteGoal(id: Int)

    suspend fun insertTransaction(transaction: GoalTransaction)
    suspend fun getTransactionsForGoal(goalId: String): Flow<List<GoalTransaction>>
    suspend fun getAllTransactions(): Flow<List<GoalTransaction>>
    suspend fun getSavedAmount(goalId: Int): Long?
}

class GoalRepositoryImpl(
    private val dataSource: GoalLocalDataSource
) : GoalRepository {

    override suspend fun getAllGoals(): Flow<List<GoalWithSavedAmount>> = dataSource.getAllGoals()

    override suspend fun insertGoal(goal: Goal) = dataSource.insertGoal(goal)

    override suspend fun getGoalById(id: Int): Flow<GoalWithSavedAmount> = dataSource.getGoalById(id)

    override suspend fun updatePinStatus(goal: Goal) = dataSource.updatePinStatus(goal)

    override suspend fun updateGoal(goal: Goal?) = dataSource.updateGoal(goal)

    override suspend fun deleteGoal(id: Int) = dataSource.deleteGoal(id)

    override suspend fun insertTransaction(transaction: GoalTransaction) =
        dataSource.insertTransaction(transaction)

    override suspend fun getTransactionsForGoal(goalId: String): Flow<List<GoalTransaction>> =
        dataSource.getTransactionsForGoal(goalId)

    override suspend fun getAllTransactions(): Flow<List<GoalTransaction>> =
        dataSource.getAllTransactions()

    override suspend fun getSavedAmount(goalId: Int): Long? = dataSource.getSavedAmount(goalId)
}