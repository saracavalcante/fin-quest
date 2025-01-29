package br.com.finquest.core.database.datasource

import br.com.finquest.core.database.dao.GoalDao
import br.com.finquest.core.database.model.asEntity
import br.com.finquest.core.database.model.asModel
import br.com.finquest.core.model.data.Goal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GoalLocalDataSource {
    suspend fun getAllGoals(): Flow<List<Goal>>
    suspend fun insertGoal(goal: Goal)
    suspend fun getGoalById(id: Int): Flow<Goal>
    suspend fun updateGoal(goal: Goal)
    suspend fun deleteGoal(goal: Goal)
}

class GoalLocalDataSourceImpl(private val goalDao: GoalDao) : GoalLocalDataSource {

    override suspend fun getAllGoals(): Flow<List<Goal>> = goalDao.getAllGoals().map {
        it.map { goalEntity -> goalEntity.asModel() }
    }

    override suspend fun insertGoal(goal: Goal) = goalDao.insertGoal(goal.asEntity())

    override suspend fun getGoalById(id: Int): Flow<Goal> = goalDao.getGoalById(id).map {
        it.asModel()
    }

    override suspend fun updateGoal(goal: Goal) = goalDao.updateGoal(goal.asEntity())

    override suspend fun deleteGoal(goal: Goal) = goalDao.deleteGoal(goal.asEntity())
}