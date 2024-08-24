package com.jpc.musicdelightapplication.data

import com.jpc.lib_base.data.bean.ApiResponse
import com.jpc.lib_base.http.RetrofitManager
import com.jpc.musicdelightapplication.data.bean.BannerData
import com.jpc.musicdelightapplication.data.bean.BannerListData
import com.jpc.musicdelightapplication.data.bean.PlaylistData
import com.jpc.musicdelightapplication.data.bean.PlaylistTagData
import com.jpc.musicdelightapplication.data.bean.QrCodeKeyData
import com.jpc.musicdelightapplication.data.http.Api

object DataRepository: Api{
    private val service by lazy { RetrofitManager.getService(Api::class.java) }

//    override suspend fun getQrCodeKey(): ApiResponse<QrCodeKeyData> {
//        return service.getQrCodeKey()
//    }

    override suspend fun getBanner(): ApiResponse<List<BannerData>> {
        return service.getBanner()
    }

    override suspend fun getRankingList(): ApiResponse<List<PlaylistData>> {
        return service.getRankingList()
    }

    override suspend fun getPlaylistTagList(): ApiResponse<List<PlaylistTagData>> {
        return service.getPlaylistTagList()
    }
}