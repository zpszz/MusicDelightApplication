package com.jpc.musicdelightapplication.data.bean

import com.google.gson.annotations.SerializedName

/**
 * 歌手数据
 */
data class ArtistData(
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("tns")
    val tns: List<Any> = listOf(),
    @SerializedName("alias")
    val alias: List<Any> = listOf()
)