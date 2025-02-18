package br.com.finquest.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import br.com.finquest.core.model.data.GoalTransaction
import java.time.LocalDateTime

@Entity(
    tableName = "goal_transactions",
    foreignKeys = [
        ForeignKey(
            entity = GoalEntity::class,
            parentColumns = ["id"],
            childColumns = ["goalId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GoalTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val goalId: Int,
    val amount: Long,
    val dateAdded: LocalDateTime = LocalDateTime.now()
)

fun GoalTransaction.asEntity() = GoalTransactionEntity(
    id = id ?: 0,
    name = name ?: "",
    goalId = goalId ?: 0,
    amount = amount ?: 0,
    dateAdded = dateAdded
)

fun GoalTransactionEntity.asModel() = GoalTransaction(
    id = id,
    name = name,
    goalId = goalId,
    amount = amount,
    dateAdded = dateAdded
)