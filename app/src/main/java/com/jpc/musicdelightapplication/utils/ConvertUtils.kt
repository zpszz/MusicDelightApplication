package com.jpc.musicdelightapplication.utils


import com.jpc.lib_base.ext.divide
import com.jpc.lib_base.ext.format
import java.math.RoundingMode

/**
 * 用于格式化数据
 */
object ConvertUtils {

    fun formatPlayCount(num: Long, dot: Int = 0): String {
        return if (num < 100000) {
            num.toString()
        } else if (num < 100000000) {
            val wan = num.toDouble().divide(10000.0).format(dot, RoundingMode.HALF_DOWN)
            wan + "万"
        } else {
            val wan = num.toDouble().divide(100000000.0).format(dot, RoundingMode.HALF_DOWN)
            wan + "亿"
        }
    }
}