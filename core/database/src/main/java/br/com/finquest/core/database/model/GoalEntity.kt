package br.com.finquest.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.finquest.core.model.data.Goal

@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val icon: Int,
    val color: Int,
    val targetAmount: Long,
    val savedAmount: Long,
    val deadline: String?,
    val status: String
)

fun Goal.asEntity() = GoalEntity(
    id = id,
    name = name,
    icon = icon,
    color = color,
    targetAmount = targetAmount,
    savedAmount = savedAmount,
    deadline = deadline,
    status = status
)

fun GoalEntity.asModel() = Goal(
    id = id,
    name = name,
    icon = icon,
    color = color,
    targetAmount = targetAmount,
    savedAmount = savedAmount,
    deadline = deadline,
    status = status
)