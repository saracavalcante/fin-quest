package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository
import br.com.finquest.core.model.data.Goal

class AddGoalUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(goal: Goal): Long {
        return repository.insertGoal(goal)
    }
}