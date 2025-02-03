package br.com.finquest.core.utils

import java.util.Locale

class BalanceFormatter {
    fun formatInput(currentValue: String, newInput: String): String {
        val numericValue = currentValue.replace("\\D".toRegex(), "")

        val updatedValue = when (newInput) {
            "DEL" -> {
                if (numericValue.isNotEmpty()) {
                    numericValue.dropLast(1)
                } else ""
            }
            else -> {
                numericValue + newInput
            }
        }

        return if (updatedValue.isNotEmpty()) {
            val parsedValue = updatedValue.toLong()
            String.format(
                Locale("pt", "BR"),
                "%,.2f",
                parsedValue / 100.0
            ).replace('.', ',')
        } else "R$ 0,00"
    }
}