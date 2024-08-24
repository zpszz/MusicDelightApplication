package com.jpc.musicdelightapplication.ui.square

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.jpc.musicdelightapplication.R
import com.jpc.musicdelightapplication.data.bean.PlaylistData
import com.jpc.musicdelightapplication.databinding.ItemRecommendPlayListBinding

class ItemPlaylistAdapter(private val itemWidth: Int) :
    BaseQuickAdapter<PlaylistData, BaseDataBindingHolder<ItemRecommendPlayListBinding>>(
        R.layout.item_recommend_play_list
    ), LoadMoreModule {
    init {
        setAnimationWithDefault(AnimationType.SlideInBottom)
    }

    override fun convert(
        holder: BaseDataBindingHolder<ItemRecommendPlayListBinding>,
        item: PlaylistData
    ) {
        holder.dataBinding?.apply {
            playlist = item
            val lp = ivCover.layoutParams
            lp.width = itemWidth
            lp.height = itemWidth
            executePendingBindings()
            // 处理点击事件
        }
    }

}