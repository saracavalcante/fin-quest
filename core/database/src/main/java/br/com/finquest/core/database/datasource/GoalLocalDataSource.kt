package br.com.finquest.core.database.datasource

import br.com.finquest.core.database.dao.GoalDao
import br.com.finquest.core.database.dao.GoalTransactionDao
import br.com.finquest.core.database.model.asEntity
import br.com.finquest.core.database.model.asModel
import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.model.data.GoalTransaction
import br.com.finquest.core.model.data.GoalWithSavedAmount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GoalLocalDataSource {
    suspend fun getAllGoals(): Flow<List<GoalWithSavedAmount>>
    suspend fun insertGoal(goal: Goal): Long
    suspend fun getGoalById(id: Int): Flow<GoalWithSavedAmount>
    suspend fun updatePinStatus(goal: Goal)
    suspend fun updateGoal(goal: Goal?)
    suspend fun deleteGoal(id: Int)

    suspend fun insertTransaction(transaction: GoalTransaction)
    suspend fun getTransactionsForGoal(goalId: String): Flow<List<GoalTransaction>>
    suspend fun getSavedAmount(goalId: Int): Long?
}

class GoalLocalDataSourceImpl(
    private val goalDao: GoalDao,
    private val goalTransactionDao: GoalTransactionDao
) : GoalLocalDataSource {

    override suspend fun getAllGoals(): Flow<List<GoalWithSavedAmount>> = goalDao.getAllGoals().map {
        it.map { goalEntity -> goalEntity.asModel() }
    }

    override suspend fun insertGoal(goal: Goal): Long =
        goalDao.insertGoal(goal.asEntity())

    override suspend fun getGoalById(id: Int): Flow<GoalWithSavedAmount> = goalDao.getGoalById(id).map {
        it.asModel()
    }

    override suspend fun updatePinStatus(goal: Goal) {
        val updateGoal = goal.copy(isPinned = !goal.isPinned)
        goalDao.updateGoal(updateGoal.asEntity())
    }

    override suspend fun updateGoal(goal: Goal?) {
        goal?.asEntity()?.let { goalDao.updateGoal(it) }
    }

    override suspend fun deleteGoal(id: Int) = goalDao.deleteGoal(id)

    override suspend fun insertTransaction(transaction: GoalTransaction) {
        goalTransactionDao.insertTransaction(transaction.asEntity())
    }

    override suspend fun getTransactionsForGoal(goalId: String): Flow<List<GoalTransaction>> =
        goalTransactionDao.getTransactionsForGoal(goalId).map {
            it.map { transaction -> transaction.asModel() }
        }

    override suspend fun getSavedAmount(goalId: Int): Long? =
        goalTransactionDao.getSavedAmount(goalId)
}