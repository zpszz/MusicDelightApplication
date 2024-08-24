package com.jpc.musicdelightapplication.data.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Banner实体
 */
@Parcelize
data class BannerData(
    val pic: String = "",
    val url: String = "",
    val bannerId: String = "",
): Parcelable

/**
 * Banner列表实体
 */
data class BannerListData(
    val banners: List<BannerData>
)