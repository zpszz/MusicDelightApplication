package com.jpc.musicdelightapplication.data.http

import com.jpc.lib_base.data.bean.ApiResponse
import com.jpc.musicdelightapplication.data.bean.BannerData
import com.jpc.musicdelightapplication.data.bean.BannerListData
import com.jpc.musicdelightapplication.data.bean.PlaylistData
import com.jpc.musicdelightapplication.data.bean.PlaylistTagData
import com.jpc.musicdelightapplication.data.bean.QrCodeKeyData
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * 请求接口
 */
interface Api {
    /**
     * 登录
     */
    // 二维码 key 生成接口
//    @GET("/login/qr/key")
//    suspend fun getQrCodeKey(
//        @Query("timestamp") timestamp: Long = System.currentTimeMillis()
//    ): ApiResponse<QrCodeKeyData>
//    // 二维码生成接口
//    @GET("login/qr/create")
//    suspend fun getLoginQrCode(
//        @Query("key") key: String,
//        @Query("timestamp") timestamp: Long = System.currentTimeMillis()
//    ): NetResult<QrCodeData>
//    // 二维码检测扫码状态接口
//    @GET("login/qr/check")
//    suspend fun checkLoginStatus(
//        @Query("key") key: String,
//        @Query("timestamp") timestamp: Long = System.currentTimeMillis(),
//        @Query("noCookie") noCookie: Boolean = true
//    ): LoginResultData




    // 获取Banner
    @GET("/banner?type=1")
    suspend fun getBanner(): ApiResponse<List<BannerData>>

    // 获取推荐歌单，需要登录
    //@GET("recommend/resource")
    //suspend fun getRecommendPlaylist():

    // 所有榜单
    @GET("toplist")
    suspend fun getRankingList(): ApiResponse<List<PlaylistData>>

    // ================== 歌单广场 ==================
    // 热门歌单分类
    @GET("playlist/hot")
    suspend fun getPlaylistTagList(): ApiResponse<List<PlaylistTagData>>
}