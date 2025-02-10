package br.com.finquest.features.home.ui.addamount

data class AddAmountUiState(
    val isLoading: Boolean = false,
    val balance: String = "",
    val error: Throwable? = null
)