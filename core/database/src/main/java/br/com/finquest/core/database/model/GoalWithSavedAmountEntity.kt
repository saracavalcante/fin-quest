package br.com.finquest.core.database.model

import androidx.room.Embedded
import br.com.finquest.core.model.data.GoalWithSavedAmount

data class GoalWithSavedAmountEntity(
    @Embedded val goal: GoalEntity,
    val savedAmount: Long?
)

fun GoalWithSavedAmountEntity.asModel() = GoalWithSavedAmount(
    goal = goal.asModel(),
    savedAmount = savedAmount
)