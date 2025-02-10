package br.com.finquest.core.common.util

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun currentDate(): String {
    val today = LocalDate.now()
    val locale = Locale.forLanguageTag("pt-BR")
    val dayOfWeek = today.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)
    val day = today.dayOfMonth
    val month = today.month.getDisplayName(TextStyle.FULL, locale)

    return "$dayOfWeek, $day de $month"
}