package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository

class GetSavedAmountUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(goalId: Int): Long? {
        return repository.getSavedAmount(goalId)
    }
}