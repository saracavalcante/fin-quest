package br.com.finquest.core.common.util

import java.awt.Color

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