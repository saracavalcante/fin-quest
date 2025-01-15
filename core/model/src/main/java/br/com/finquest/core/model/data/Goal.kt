package br.com.finquest.core.model.data

data class Goal(
    val title: String,
    val icon: Int?,
    val amount: Long,
    val deadline: String,
    val status: String
)