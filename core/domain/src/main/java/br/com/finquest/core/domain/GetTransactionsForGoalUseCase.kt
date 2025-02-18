package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository
import br.com.finquest.core.model.data.GoalTransaction
import kotlinx.coroutines.flow.Flow

class GetTransactionsForGoalUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(goalId: Int?): Flow<List<GoalTransaction>> {
        return if (goalId == null) {
            repository.getAllTransactions()
        } else {
            repository.getTransactionsForGoal(goalId.toString())
        }
    }
}