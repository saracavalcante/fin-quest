package br.com.finquest.core.model.data

data class Goal(
    val id: Int? = null,
    val name: String,
    val icon: Int,
    val color: Int,
    val targetAmount: Long,
    val savedAmount: Long,
    val deadline: String? = null,
    val status: String
)