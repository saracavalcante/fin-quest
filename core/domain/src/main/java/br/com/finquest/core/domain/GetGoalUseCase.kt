package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository
import br.com.finquest.core.model.data.GoalWithSavedAmount
import kotlinx.coroutines.flow.Flow

class GetGoalUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(id: Int): Flow<GoalWithSavedAmount> {
        return repository.getGoalById(id)
    }
}