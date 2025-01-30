package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository
import br.com.finquest.core.model.data.Goal

class PinGoalUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(goal: Goal) {
        repository.updatePinStatus(goal)
    }
}