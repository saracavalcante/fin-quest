package br.com.finquest.core.domain

import br.com.finquest.core.data.repository.GoalRepository
import br.com.finquest.core.model.data.GoalTransaction

class InsertTransactionUseCase(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(transaction: GoalTransaction) {
        repository.insertTransaction(transaction)
    }
}