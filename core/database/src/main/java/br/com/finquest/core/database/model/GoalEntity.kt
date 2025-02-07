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
    val status: String,
    val isPinned: Boolean = false,
    val isPaused: Boolean = false
)

fun Goal.asEntity() = GoalEntity(
    id = id,
    name = name ?: "",
    icon = icon ?: 0,
    color = color ?: 0,
    targetAmount = targetAmount ?: 0,
    savedAmount = savedAmount ?: 0,
    deadline = deadline,
    status = status ?: "",
    isPinned = isPinned,
    isPaused = isPaused
)

fun GoalEntity.asModel() = Goal(
    id = id,
    name = name,
    icon = icon,
    color = color,
    targetAmount = targetAmount,
    savedAmount = savedAmount,
    deadline = deadline,
    status = status,
    isPinned = isPinned,
    isPaused = isPaused
)