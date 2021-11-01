package com.joelysondavid.lend.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

fun Date.dateToString(format: String = "dd-MM-yyyy"): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}

@SuppressLint("SimpleDateFormat")
fun String.stringToDate(format: String = "dd-MM-yyyy"): Date? {
    val date = SimpleDateFormat(format).parse(this)
    return date
}