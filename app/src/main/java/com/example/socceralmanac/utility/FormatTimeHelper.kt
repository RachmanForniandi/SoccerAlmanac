package com.example.socceralmanac.utility

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getStringDate(date: String): String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val output = SimpleDateFormat("EEE, dd MMM yyy")
    var t: Date? = null
    try {
        t = dateFormat.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return output.format(t)
}

@SuppressLint("SimpleDateFormat")
fun getStringTime(time: String): String? {
    val timeFormat = SimpleDateFormat("HH:mm")
    val output = SimpleDateFormat("hh:mm a")
    var t: Date? = null
    try {
        t = timeFormat.parse(time)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return output.format(t)
}

@SuppressLint("SimpleDateFormat")
fun getCalendar(dates: String, times: String): Long {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val timeFormat = SimpleDateFormat("HH:mm")

    val year = SimpleDateFormat("yyy")
    val month = SimpleDateFormat("MM")
    val date = SimpleDateFormat("dd")
    val hour = SimpleDateFormat("hh")
    val minute = SimpleDateFormat("mm")

    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.set(
        year.format(dateFormat.parse(dates)).toInt() - 0,
        month.format(dateFormat.parse(dates)).toInt() - 1,
        date.format(dateFormat.parse(dates)).toInt() - 0,
        hour.format(timeFormat.parse(times)).toInt() - 0,
        minute.format(timeFormat.parse(times)).toInt() - 0)

    return calendar.timeInMillis
}