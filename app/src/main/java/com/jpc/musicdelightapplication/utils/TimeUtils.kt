package com.jpc.musicdelightapplication.utils

import android.text.format.DateUtils
import java.util.Locale

/**
 * 时间工具类
 */
object TimeUtils {
    fun formatMs(milli: Long): String {
        return formatTime("mm:ss", milli)
    }

    fun formatTime(pattern: String, milli: Long): String {
        val m = (milli / DateUtils.MINUTE_IN_MILLIS).toInt()
        val s = (milli / DateUtils.SECOND_IN_MILLIS % 60).toInt()
        val mm = String.format(Locale.getDefault(), "%02d", m)
        val ss = String.format(Locale.getDefault(), "%02d", s)
        return pattern.replace("mm", mm).replace("ss", ss)
    }
}