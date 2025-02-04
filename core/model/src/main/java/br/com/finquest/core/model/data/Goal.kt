package br.com.finquest.core.model.data

data class Goal(
    val id: Int? = null,
    val name: String? = null,
    val icon: Int? = null,
    val color: Int? = null,
    val targetAmount: Long? = null,
    val savedAmount: Long? = null,
    val deadline: String? = null,
    val status: String? = null,
    val isPinned: Boolean = false
)