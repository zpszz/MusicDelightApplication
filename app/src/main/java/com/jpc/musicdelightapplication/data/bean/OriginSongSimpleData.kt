package com.jpc.musicdelightapplication.data.bean

import com.google.gson.annotations.SerializedName

/**
 * 原始歌曲简单数据
 */
data class OriginSongSimpleData(
    @SerializedName("songId")
    val songId: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("artists")
    val artists: List<ArtistData> = listOf(),
    @SerializedName("albumMeta")
    val albumMeta: AlbumData = AlbumData()
)