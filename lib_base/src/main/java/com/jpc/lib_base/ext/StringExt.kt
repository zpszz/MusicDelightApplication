package com.jpc.lib_base.ext

import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.google.gson.Gson

/**
 * String扩展类
 * 作用：将HTML字符串转为Spanned，支持Html标签
 */
fun String.toHtml(flag: Int = HtmlCompat.FROM_HTML_MODE_LEGACY): Spanned {
    return HtmlCompat.fromHtml(this, flag)
}

/** 将对象转为JSON字符串 */
fun Any?.toJson(): String {
    return Gson().toJson(this)
}