package com.jpc.musicdelightapplication

import com.jpc.lib_base.BaseApplication
import com.jpc.musicdelightapplication.base.AppViewModel

/**
 * 自定义 Application，用于初始化一些全局配置
 */
class MyApplication: BaseApplication(){
    companion object{
        lateinit var appViewModel: AppViewModel
    }

    override fun onCreate() {
        super.onCreate()
        appViewModel = getAppViewModelProvider()[AppViewModel::class.java]

    }
}