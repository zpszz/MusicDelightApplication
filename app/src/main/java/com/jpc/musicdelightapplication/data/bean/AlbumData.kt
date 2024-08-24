package com.jpc.musicdelightapplication.data.bean

import com.google.gson.annotations.SerializedName
import com.jpc.musicdelightapplication.ext.asLargeCover
import com.jpc.musicdelightapplication.ext.asSmallCover

/**
 * 专辑实体
 */
data class AlbumData(
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("name")
    val name: String = "",
    @Deprecated("Please use resized url")
    @SerializedName("picUrl")
    val picUrl: String = "",
    @SerializedName("tns")
    val tns: List<Any> = listOf(),
    @SerializedName("pic_str")
    val picStr: String = "",
    @SerializedName("pic")
    val pic: Long = 0
) {
    fun getSmallCover(): String {
        return picUrl.asSmallCover()
    }

    fun getLargeCover(): String {
        return picUrl.asLargeCover()
    }
}
