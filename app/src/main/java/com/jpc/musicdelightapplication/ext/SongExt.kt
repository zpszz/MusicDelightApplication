package com.jpc.musicdelightapplication.ext

/**
 * 获取小图
 */
fun String.asSmallCover(): String {
    return appendImageSize(200)
}

/**
 * 获取大图
 */
fun String.asLargeCover(): String {
    return appendImageSize(800)
}

/**
 * 拼接图片尺寸
 */
private fun String.appendImageSize(size: Int): String {
    return if (contains("?")) {
        "$this&param=${size}y${size}"
    } else {
        "$this?param=${size}y${size}"
    }
}

