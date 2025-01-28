package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository
import br.com.finquest.core.model.data.Goal
import kotlinx.coroutines.flow.Flow

class GetAllGoalsUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(): Flow<List<Goal>> {
        return repository.getAllGoals()
    }
}