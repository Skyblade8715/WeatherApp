package com.skycom.weatherapp.core.common.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun formatTimestampToDateAndTime(timestamp: Long): String {
    return SimpleDateFormat(
        "dd.MM EEE, HH:mm",
        Locale.getDefault()
    ).format(Date(timestamp * 1000))
}

fun formatTimestampToMilitaryTime(timestamp: Long): String {
    return SimpleDateFormat(
        "HH:mm",
        Locale.getDefault()
    ).format(Date(timestamp * 1000))
}