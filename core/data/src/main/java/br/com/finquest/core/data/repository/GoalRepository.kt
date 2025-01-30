package br.com.finquest.core.data.repository

import br.com.finquest.core.database.datasource.GoalLocalDataSource
import br.com.finquest.core.model.data.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {
    suspend fun getAllGoals(): Flow<List<Goal>>
    suspend fun insertGoal(goal: Goal)
    suspend fun getGoalById(id: Int): Flow<Goal>
    suspend fun updatePinStatus(goal: Goal)
    suspend fun updateGoal(goal: Goal)
    suspend fun deleteGoal(id: Int)
}

class GoalRepositoryImpl(
    private val dataSource: GoalLocalDataSource
): GoalRepository {

    override suspend fun getAllGoals(): Flow<List<Goal>> = dataSource.getAllGoals()

    override suspend fun insertGoal(goal: Goal) = dataSource.insertGoal(goal)

    override suspend fun getGoalById(id: Int): Flow<Goal> = dataSource.getGoalById(id)

    override suspend fun updatePinStatus(goal: Goal) = dataSource.updatePinStatus(goal)

    override suspend fun updateGoal(goal: Goal) = dataSource.updateGoal(goal)

    override suspend fun deleteGoal(id: Int) = dataSource.deleteGoal(id)
}