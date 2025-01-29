package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository
import br.com.finquest.core.model.data.Goal
import kotlinx.coroutines.flow.Flow

class GetGoalUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(id: Int): Flow<Goal> {
        return repository.getGoalById(id)
    }
}