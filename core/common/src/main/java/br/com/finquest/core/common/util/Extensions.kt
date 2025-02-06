package br.com.finquest.core.common.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun String.toCents(): Long {
    return this.replace(".", "")
        .replace(",", ".")
        .toBigDecimal()
        .multiply(100.toBigDecimal())
        .toLong()
}

fun Long.toMoneyString(): String {
    val real = this / 100
    val cents = this % 100
    return "%d,%02d".format(real, cents)
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