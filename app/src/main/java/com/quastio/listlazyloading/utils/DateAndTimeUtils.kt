package com.quastio.listlazyloading.utils

import java.text.SimpleDateFormat
import java.util.*

object DateAndTimeUtils {
    fun getDate(date: String): String? {
        var date = date
        return try {
            date = date.replace("T", " ").replace("Z", "")
            val sdf =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val dateUtc = sdf.parse(date)
            val sdfLocal =
                SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            sdfLocal.format(dateUtc)
        } catch (e: Exception) {
            ""
        }
    }

    fun getTime(date: String): String? {
        var date = date
        return try {
            date = date.replace("T", " ").replace("Z", "")
            val sdf =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val dateUtc = sdf.parse(date)
            val sdfLocal =
                SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            sdfLocal.format(dateUtc)
        } catch (e: Exception) {
            ""
        }
    }

}