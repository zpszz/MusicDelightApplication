package com.jpc.lib_base.ext

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 扩展Double类型的除法运算
 */
fun Double.divide(divisor: Double):Double{
    if (divisor == 0.0){
        throw IllegalArgumentException("The divisor can't be zero.")
    }
    return this / divisor
}

/**
 * 扩展Double类型的四舍五入
 */
fun Double.format(decimalPlaces: Int, roundingMode: RoundingMode): String{
    // 使用BigDecimal进行四舍五入
    return BigDecimal(this).setScale(decimalPlaces, roundingMode).toString()
}