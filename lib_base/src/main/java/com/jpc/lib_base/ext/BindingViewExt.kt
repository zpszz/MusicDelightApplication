package com.jpc.lib_base.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
//import com.jpc.lib_base.widget.TitleLayout

/**
 * DataBinding的自定义属性
 */
@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    load(url)
}

/**
 * ImageView设置圆形图片
 */
@BindingAdapter("circleImageUrl")
fun ImageView.setCircleImageUrl(url: String) {
    loadCircle(url)
}

/**
 * 这里使用DataBinding的自定义属性方式而不是直接用TitleLayout的app:titleText，
 * 是因为TitleLayout里定义的setTitleText方法返回值是TitleLayout对象而不是void，
 * 不想去改TitleLayout里定义的setTitleText方法所以就用DataBinding的自定义属性
 */
//@BindingAdapter("titleText")
//fun TitleLayout.setTitle(titleText: String?) {
//    this.setTitleText(titleText ?: "")
//}