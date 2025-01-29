package br.com.finquest.core.common.util

import java.awt.Color
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    return try {
        LocalDate.parse(this, dateFormatter)
    } catch (e: Exception) {
        null
    }
}