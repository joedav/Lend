package com.joelysondavid.lend.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.dateToString(format: String = "dd-MM-yyyy"): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}

fun Date.stringToDate(format: String = "dd-MM-yyyy"): Date? {
    return SimpleDateFormat(format, Locale.getDefault()).parse(this.toString())
}