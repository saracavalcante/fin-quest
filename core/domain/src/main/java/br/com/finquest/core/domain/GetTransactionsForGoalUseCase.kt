package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository

class GetTransactionsForGoalUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(goalId: String) {
        repository.getTransactionsForGoal(goalId)
    }
}