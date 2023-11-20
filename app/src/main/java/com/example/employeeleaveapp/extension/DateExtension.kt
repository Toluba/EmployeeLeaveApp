package com.example.employeeleaveapp.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toFormattedDateString(): String {
    val sdf = SimpleDateFormat("EEEE, LLLL dd", Locale.getDefault())
    return sdf.format(this)
}

fun Date.toFormattedMonthDateString(): String {
    val sdf = SimpleDateFormat("MMMM dd", Locale.getDefault())
    return sdf.format(this)
}

fun Date.toFormattedDateShortString(): String {
    val sdf = SimpleDateFormat("dd", Locale.getDefault())
    return sdf.format(this)
}

fun Long.toFormattedDateString(): String {
    val sdf = SimpleDateFormat("LLLL dd, yyyy", Locale.getDefault())
    return sdf.format(this)
}
