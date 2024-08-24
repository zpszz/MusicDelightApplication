package com.jpc.lib_base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jpc.lib_base.data.bean.ApiResponse

/**
 * ViewModel基类
 */
abstract class BaseViewModel: ViewModel(){
    // exception用于监听全局异常，例如请求失败、服务器连接超时等
    val exception = MutableLiveData<Exception>()
    // errorResponse用于监听请求服务器返回错误，例如请求成功但是status错误，如登录过期
    val errorResponse = MutableLiveData<ApiResponse<*>?>()
    // start方法用于界面启动时要进行的初始化操作，例如网络请求、数据初始化等，由子类实现
    abstract fun start()
}