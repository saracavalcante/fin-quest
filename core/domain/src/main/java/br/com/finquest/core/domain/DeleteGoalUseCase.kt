package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository

class DeleteGoalUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteGoal(id)
    }
}