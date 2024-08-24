package com.jpc.lib_base.utils

import android.app.Activity
import android.view.WindowManager
import com.jpc.lib_base.R

/**
 * 设置沉浸式状态栏
 */
object StatusBarUtil {
    /**
     * 模拟沉浸式状态栏，本质上是通过设置状态栏的颜色，可设置为与toolbar相同达到沉浸式的效果
     *
     * @param activity 要设置的Activity
     */
    fun setImmersionStatus(activity: Activity) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity.window.statusBarColor = activity.resources.getColor(R.color.color_primary)
    }

    /**
     * 设置无状态栏，直接干掉顶部的状态栏，但要注意例如一些actionbar会自动顶到最上方需要适配
     *
     * @param activity 要设置的Activity
     */
    fun setNoStatus(activity: Activity) {
        // 透明状态栏
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}