package br.com.finquest.core.utils

import java.text.NumberFormat
import java.util.Locale


class BalanceFormatter {
    private val locale = Locale("pt", "BR")
    private val numberFormat = NumberFormat.getNumberInstance(locale).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    fun formatInput(currentValue: String, newInput: String): String {
        val numericValue = currentValue.replace("\\D".toRegex(), "")

        val updatedValue = when (newInput) {
            "DEL" -> if (numericValue.isNotEmpty()) numericValue.dropLast(1) else ""
            else -> numericValue + newInput
        }

        if (updatedValue.isEmpty()) return "0,00"

        val parsedValue = updatedValue.toLongOrNull() ?: 0L
        val formattedValue = numberFormat.format(parsedValue / 100.0)
        return formattedValue
    }
}