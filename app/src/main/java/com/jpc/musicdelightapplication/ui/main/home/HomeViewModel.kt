package com.jpc.musicdelightapplication.ui.main.home

import com.jpc.lib_base.base.BaseViewModel
import com.jpc.lib_base.ext.handleRequest
import com.jpc.lib_base.ext.launch
import com.jpc.musicdelightapplication.data.DataRepository
import com.jpc.musicdelightapplication.data.bean.BannerData
import kotlinx.coroutines.flow.MutableStateFlow
/**
 * 首页ViewModel
 */
class HomeViewModel: BaseViewModel(){
    // Banner列表
    private val _bannerListStateFlow = MutableStateFlow<List<BannerData>>(emptyList()) // 需要传入一个空列表，这是因为StateFlow需要一个初始值
    val bannerListStateFlow = _bannerListStateFlow

    override fun start() {

    }

    /**
     * 请求Banner数据
     */
    fun fetchBanner() {
        launch({
            handleRequest(DataRepository.getBanner(), {
                _bannerListStateFlow.value = it.data
            })
        })
    }
}