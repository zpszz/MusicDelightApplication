package com.jpc.lib_base.ext

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jpc.lib_base.R
import com.jpc.lib_base.utils.ScreenUtil

/**
 * ImageView利用Glide加载图片
 * @param url 图片url（可远程可本地）
 * @param showPlaceholder 是否展示placeholder默认图片，默认为true
 */
fun ImageView.load(url: String, showPlaceholder: Boolean = true) {
    if (showPlaceholder) {
        Glide.with(context).load(url)
            .placeholder(R.drawable.ic_default_img)
            // 设置渐变效果，这里是500ms
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(this)
    } else {
        Glide.with(context).load(url)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(this)
    }
}

/**
 * ImageView利用Glide加载圆形图片
 * @param url 图片url（可远程可本地）
 */
fun ImageView.loadCircle(url: String) {
    Glide.with(context).load(url)
        .placeholder(R.drawable.ic_default_img)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .transition(DrawableTransitionOptions.withCrossFade(500))
        .into(this)
}

/**
 * SwipeRefreshLayout设置加载主题颜色
 */
fun SwipeRefreshLayout.initColors() {
    setColorSchemeResources(
        R.color.color_primary, android.R.color.holo_red_light,
        android.R.color.holo_orange_light, android.R.color.holo_green_light
    )
}

/**
 * RecyclerView列表为空时的显示视图
 */
fun RecyclerView.getEmptyView(message: String = "列表为空"): View {
    return LayoutInflater.from(context)
        .inflate(R.layout.layout_empty, parent as ViewGroup, false).apply {
            findViewById<TextView>(R.id.tv_empty).text = message
        }
}

/**
 * 初始化普通的toolbar 只设置标题
 *
 * @param titleStr 标题
 */
fun Toolbar.initTitle(titleStr: String = "") {
    title = titleStr
}

/**
 * 初始化返回键
 *
 * @param backImg 返回键资源路径
 * @param onBack 返回事件
 */
fun Toolbar.initClose(
    backImg: Int = R.drawable.ic_back,
    onBack: () -> Unit
) {
    setNavigationIcon(backImg)
    setNavigationOnClickListener { onBack() }
}

/**
 * Activity上显示AlertDialog
 *
 * @param message AlertDialog内容信息
 * @param title AlertDialog标题，默认为 "温馨提示"
 * @param positiveButtonText AlertDialog右侧按键内容 默认为 "确定"
 * @param positiveAction AlertDialog点击右侧按键的行为 默认是空方法
 * @param negativeButtonText AlertDialog左侧按键内容 默认为 "取消"
 * @param negativeAction AlertDialog点击左侧按键的行为 默认是空方法
 */
fun AppCompatActivity.showDialog(
    message: String,
    title: String = "温馨提示",
    positiveButtonText: String = "确定",
    positiveAction: () -> Unit = {},
    negativeButtonText: String = "取消",
    negativeAction: () -> Unit = {}
) {
    MaterialDialog(this)
        .cancelable(true)
        // this指代当前的Activity，MaterialDialog需要设置生命周期所有者
        .lifecycleOwner(this)
        .show {
            title(text = title)
            message(text = message)
            // invoke()方法是Kotlin中的一种调用函数的方式，可以理解为调用函数
            positiveButton(text = positiveButtonText) { positiveAction.invoke() }
            negativeButton(text = negativeButtonText) { negativeAction.invoke() }
        }
}

/**
 * Fragment上显示AlertDialog
 *
 * @param message AlertDialog内容信息
 * @param title AlertDialog标题，默认为 "温馨提示"
 * @param positiveButtonText AlertDialog右侧按键内容 默认为 "确定"
 * @param positiveAction AlertDialog点击右侧按键的行为 默认是空方法
 * @param negativeButtonText AlertDialog左侧按键内容 默认为 "取消"
 * @param negativeAction AlertDialog点击左侧按键的行为 默认是空方法
 */
fun Fragment.showDialog(
    message: String,
    title: String = "温馨提示",
    positiveButtonText: String = "确定",
    positiveAction: () -> Unit = {},
    negativeButtonText: String = "取消",
    negativeAction: () -> Unit = {}
) {
    MaterialDialog(requireContext())
        .cancelable(true)
        // 设置生命周期所有者，这里和Activity不同，Fragment需要设置viewLifecycleOwner
        // 因为viewLifecycleOwner是Fragment的生命周期所有者，而Fragment的生命周期和Activity的生命周期不同
        .lifecycleOwner(viewLifecycleOwner)
        .show {
            title(text = title)
            message(text = message)
            positiveButton(text = positiveButtonText) { positiveAction.invoke() }
            negativeButton(text = negativeButtonText) { negativeAction.invoke() }
        }
}

/**
 * 加载框
 * @SuppressLint("StaticFieldLeak") 注解表示忽略内存泄漏警告，需要自己在代码中手动hideLoading，避免内存泄漏
 */
@SuppressLint("StaticFieldLeak")
private var mLoadingDialog: MaterialDialog? = null

/**
 * 在Activity中显示加载框
 * @param message 加载框的提示信息，默认为"请求网络中"
 */
fun AppCompatActivity.showLoading(message: String = "请求网络中") {
    // 如果当前Activity没有被销毁
    if (!this.isFinishing) {
        if (mLoadingDialog == null) {
            mLoadingDialog = MaterialDialog(this)
                .cancelable(true)
                // 设置点击外部不可取消
                .cancelOnTouchOutside(false)
                .cornerRadius(6f)
                // 设置自定义视图
                .customView(R.layout.dialog_loading)
                // 设置最大宽度，这里需要转换dp为px，因为MaterialDialog中的maxWidth是px
                .maxWidth(literal = ScreenUtil.dp2px(this, 120f))
                .lifecycleOwner(this)
                .apply {
                    getCustomView().findViewById<TextView>(R.id.tv_loadingMsg).text = message
                }
        }
        mLoadingDialog?.show()
    }
}

/**
 * 在Fragment中显示加载框
 * @param message 加载框的提示信息，默认为"请求网络中"
 */
fun Fragment.showLoading(message: String = "请求网络中") {
    // 如果当前Fragment没有被移除
    if (!this.isRemoving) {
        if (mLoadingDialog == null) {
            // requireContext()获取上下文，这是Fragment中的方法
            mLoadingDialog = MaterialDialog(requireContext())
                .cancelable(true)
                .cancelOnTouchOutside(false)
                .cornerRadius(6f)
                .customView(R.layout.dialog_loading)
                .maxWidth(literal = ScreenUtil.dp2px(requireContext(), 120f))
                // 这里为什么是this，因为Fragment是Fragment的生命周期所有者
                .lifecycleOwner(this)
                .apply {
                    getCustomView().findViewById<TextView>(R.id.tv_loadingMsg).text = message
                }
        }
        mLoadingDialog?.show()
    }
}

/**
 * 隐藏加载框，这里需要判断加载框是否为空，因为加载框可能已经被销毁
 * 最后将加载框置为null，可以避免内存泄漏
 */
fun hideLoading() {
    mLoadingDialog?.dismiss()
    mLoadingDialog = null
}