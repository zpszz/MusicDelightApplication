package com.jpc.musicdelightapplication.ui.square

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator
import com.jpc.musicdelightapplication.R
import com.jpc.musicdelightapplication.base.BaseActivity
import com.jpc.musicdelightapplication.data.bean.PlaylistTagData
import com.jpc.musicdelightapplication.databinding.ActivitySquareBinding

class SquareActivity : BaseActivity<SquareViewModel, ActivitySquareBinding>(R.layout.activity_square) {
    companion object{
        fun launch(context: Context){
            context.startActivity(Intent(context, SquareActivity::class.java))
        }
    }
    private val mTitleList = ArrayList<String>()
    private val mPlaylistTagList = ArrayList<PlaylistTagData>()
    private lateinit var mTabLayoutMediator: TabLayoutMediator
    private lateinit var mFragmentStateAdapter: FragmentStateAdapter

    override fun initView(savedInstanceState: Bundle?) {
        mFragmentStateAdapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle){
            override fun getItemCount() = mTitleList.size

            override fun createFragment(position: Int): Fragment {
                return SquareChildFragment.newInstance(mTitleList[position])
            }

        }
        mBinding.apply {
            // 由于标题也需要请求（只有请求完标题后才会加载Fragment从而显示swipeRefreshLayout），
            // 所以在请求标题之前也需要一个loading
            //showLoading = true
            viewPager2.apply {
                adapter = mFragmentStateAdapter
            }
            mTabLayoutMediator = TabLayoutMediator(tabLayout, viewPager2){tab, position ->
                tab.apply {
                    view.setOnLongClickListener{ true }
                    text = mTitleList[position]
                }
            }.apply { attach() }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun createObserve() {
        super.createObserve()
        mViewModel.apply {
            playlistTagLiveData.observe(this@SquareActivity){list ->
                //mBinding.showLoading = false
                mTitleList.apply {
                    clear()
                    add("全部")
                }
                list?.forEach { tag ->
                    mTitleList.add(tag.name)
                }
                mFragmentStateAdapter.notifyDataSetChanged()
                // 缓存所有的子Fragment，然后让子Fragment懒加载数据
                mBinding.viewPager2.offscreenPageLimit = mTitleList.size
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 解除TabLayout与ViewPager2的联动，避免内存泄漏
        // 一般来说，只要在Fragment中使用了TabLayoutMediator，就需要在Fragment销毁时调用detach()方法
        mTabLayoutMediator.detach()
    }
}