package com.jpc.lib_base.ext

import androidx.lifecycle.viewModelScope
import com.jpc.lib_base.base.BaseViewModel
import com.jpc.lib_base.data.bean.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * 启动协程，封装了viewModelScope.launch
 *
 * @param tryBlock try语句运行的函数
 * @param catchBlock catch语句运行的函数，可以用来做一些网络异常等的处理，默认空实现
 * @param finallyBlock finally语句运行的函数，可以用来做一些资源回收等，默认空实现
 */
fun BaseViewModel.launch(
    tryBlock: suspend CoroutineScope.() -> Unit,
    catchBlock: suspend CoroutineScope.() -> Unit = {},
    finallyBlock: suspend CoroutineScope.() -> Unit = {}
) {
    // 默认执行在主线程，相当于launch(Dispatchers.Main)
    viewModelScope.launch {
        try {
            tryBlock()
        } catch (e: Exception) {
            exception.value = e
            catchBlock()
        } finally {
            finallyBlock()
        }
    }
}

/**
 * 请求结果处理
 *
 * @param response ApiResponse
 * @param successBlock 服务器请求成功返回成功码的执行回调，默认空实现
 * @param errorBlock 服务器请求成功返回错误码的执行回调，默认返回false的空实现，函数返回值true:拦截统一错误处理，false:不拦截
 */
fun <T> BaseViewModel.handleRequest(
    response: ApiResponse<T>,
    successBlock: suspend CoroutineScope.(response: ApiResponse<T>) -> Unit = {},
    errorBlock: suspend CoroutineScope.(response: ApiResponse<T>) -> Boolean = { false }
) {
    viewModelScope.launch {
        when (response.code) {
            // 服务器返回请求成功码，这里需要根据实际情况修改，一般是后端约定的成功码
            200 -> successBlock(response)
            else -> {
                // 只有errorBlock返回false不拦截处理时，才去统一提醒错误提示
                if (!errorBlock(response)) {
                    errorResponse.value = response
                }
            }
        }
    }
}

/**
 * 请求结果处理(使用协程二次封装，可避免多重嵌套)
 *
 * @param response ApiResponse
 * @param handleErrorSelf 是否拦截全局的网络错误处理，默认false
 * @return Result<ApiResponse>
 */
suspend fun <T> BaseViewModel.handleRequest(
    response: ApiResponse<T>,
    handleErrorSelf: Boolean = false
): Result<ApiResponse<T>> {
    return suspendCancellableCoroutine { continuation ->
        when (response.code) {
            200 -> {
                // 这里可以根据实际情况返回不同的数据，resume表示协程执行完毕，返回结果，结果使用Result包装
                continuation.resume(Result.success(response))
            }

            else -> {
                if (!handleErrorSelf) {
                    // 只有errorBlock返回false不拦截处理时，才去统一提醒错误提示
                    errorResponse.value = response
                }
                // 这里可以根据实际情况返回不同的错误信息
                continuation.resume(Result.failure(Exception(response.message)))
            }
        }
    }
}