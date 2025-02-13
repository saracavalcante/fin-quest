package br.com.finquest.core.model.data

import java.time.LocalDateTime

data class GoalTransaction(
    val id: Int? = null,
    val goalId: Int? = null,
    val amount: Long? = null,
    val dateAdded: LocalDateTime = LocalDateTime.now()
)
