package br.com.finquest.core.common.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun String?.toCents(): Long? {
    if (this.isNullOrBlank()) return null

    return try {
        val cleanedValue = this
            .replace(Regex("[^\\d,]"), "")
            .replace(Regex("(.*),(.*),"), "$1$2,")

        val locale = Locale.forLanguageTag("pt-BR")
        val format = NumberFormat.getInstance(locale)
        val number = format.parse(cleanedValue) ?: return null

        (number.toDouble() * 100).toLong()
    } catch (e: Exception) {
        null
    }
}

fun Long.toMoneyString(): String {
    val locale = Locale.forLanguageTag("pt-BR")
    val format = NumberFormat.getCurrencyInstance(locale)
    return format.format(this / 100.0)
}

fun String?.toLocalDate(): LocalDate? {
    if (this.isNullOrBlank()) {
        return null
    }
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    return try {
        this.let { LocalDate.parse(it, dateFormatter) }
    } catch (e: Exception) {
        null
    }
}

fun Long.toFormattedDate(pattern: String = "dd/MM/yyyy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(Date(this))
}