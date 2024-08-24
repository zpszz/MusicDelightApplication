package com.jpc.musicdelightapplication.ui.main.home

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jpc.lib_base.ext.load
import com.jpc.musicdelightapplication.data.bean.BannerData
import com.youth.banner.adapter.BannerAdapter

/*
* 首页Banner的Adapter
 */
class HomeBannerAdapter(dataList: ArrayList<BannerData>): BannerAdapter<BannerData, HomeBannerAdapter.BannerViewHolder>(dataList){

    inner class BannerViewHolder(var imageView: ImageView): RecyclerView.ViewHolder(imageView)

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent?.context)
        // 必须设置为match_parent，这是ViewPager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: BannerData,
        position: Int,
        size: Int
    ) {
        holder.imageView.apply {
            load(data.pic)
            // 点击跳转
        }
    }
}