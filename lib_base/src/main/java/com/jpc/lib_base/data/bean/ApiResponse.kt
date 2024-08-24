package com.jpc.lib_base.data.bean

import com.google.gson.annotations.SerializedName

/**
 * 服务器返回数据
 */
data class ApiResponse<T> (
    val code: Int = 0,
    @SerializedName("msg", alternate = ["message"])
    val message: String = "无Message",
    @SerializedName("data", alternate = ["banners", "playlist", "recommend", "list", "tags"])
    val data: T
)