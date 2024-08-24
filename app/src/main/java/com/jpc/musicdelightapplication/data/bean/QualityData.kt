package com.jpc.musicdelightapplication.data.bean

import com.google.gson.annotations.SerializedName

/**
 * 音乐的质量数据
 */
data class QualityData(
    @SerializedName("br")
    val br: Int = 0,
    @SerializedName("fid")
    val fid: Int = 0,
    @SerializedName("size")
    val size: Int = 0,
    @SerializedName("vd")
    val vd: Int = 0,
    @SerializedName("sr")
    val sr: Int = 0
)