package com.example.lokakuis.base.extensions

import java.text.SimpleDateFormat
import java.util.*

fun formatter (fromFormat: String): SimpleDateFormat {
    val simpleDateFormat = SimpleDateFormat(fromFormat, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getTimeZone("Asia/Jakarta")

    return simpleDateFormat
}

fun parse(fromFormat: String, date: String): Date? {
    val parser = formatter(fromFormat)

    return parser.parse(date)
}

fun formatDate(from: String, fromFormat: String = "yyyy-mm-dd", intoFormat: String = "dd/mm/yyyy"): String {
    val parsed = parse(fromFormat, from)

    val formatter = formatter(intoFormat)

    return parsed?.let {
        formatter.format(it)
    } ?: ""
}
