package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository
import br.com.finquest.core.model.data.GoalTransaction
import kotlinx.coroutines.flow.Flow

class GetTransactionsForGoalUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(goalId: String): Flow<List<GoalTransaction>> {
        return repository.getTransactionsForGoal(goalId)
    }
}