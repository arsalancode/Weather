package com.tomorrow.weather.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.TextStyle
import java.util.Locale

fun formatTimeTo12Hour(time: String): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        val dateTime = LocalDateTime.parse(time, inputFormatter)
        val hour = dateTime.hour
        val suffix = if (hour < 12) "am" else "pm"
        val adjustedHour = if (hour % 12 == 0) 12 else hour % 12
        "$adjustedHour$suffix"
    } catch (ex: DateTimeParseException) {
        ex.printStackTrace()
        "?"
    }
}

fun getDayName(dateString: String): String {
    return try {
        // Define the input formatter
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        // Parse the input string into a LocalDate
        val date = LocalDate.parse(dateString, inputFormatter)
        // Get the day of the week and return its name
        date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) // e.g., "Mon"
    } catch (ex: Exception) {
        ex.printStackTrace()
        "Invalid"
    }
}

fun isDay(time: String): Boolean {
    return try {
        LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")).hour >= 12
    } catch (ex: DateTimeParseException) {
        ex.printStackTrace()
        return true
    }
}
