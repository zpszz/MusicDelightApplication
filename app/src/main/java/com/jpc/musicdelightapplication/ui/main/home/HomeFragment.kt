package com.jpc.musicdelightapplication.ui.main.home

import android.annotation.SuppressLint
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jpc.lib_base.ext.initColors
import com.jpc.musicdelightapplication.R
import com.jpc.musicdelightapplication.base.BaseFragment
import com.jpc.musicdelightapplication.data.bean.BannerData
import com.jpc.musicdelightapplication.databinding.FragmentHomeBinding
import com.jpc.musicdelightapplication.ui.ranking.RankingActivity
import com.jpc.musicdelightapplication.ui.square.SquareActivity
import com.youth.banner.indicator.CircleIndicator
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

private const val TAG = "HomeFragment"

/**
 * 首页Fragment
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {
    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    private val mBannerList = ArrayList<BannerData>()
    private val mBannerAdapter = HomeBannerAdapter(mBannerList)

    override fun initView() {
        mBinding.apply {
//            nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
//                if (scrollY == 0){
//                    // 监听滚动条到顶部
//                    mBinding.swipeRefreshLayout.isEnabled = true
//                }else{
//                    // 滚动中
//                    if (mBinding.swipeRefreshLayout.isRefreshing){
//                        mBinding.swipeRefreshLayout.isEnabled = false
//                    }
//                }
//            })

            banner.apply {
                setAdapter(mBannerAdapter)
                indicator = CircleIndicator(context)
                addBannerLifecycleObserver(viewLifecycleOwner)
            }

            swipeRefreshLayout.apply {
                initColors()
                setOnRefreshListener { onRefresh() }
            }

            tvPlayListSquare.setOnClickListener {
                SquareActivity.launch(requireContext())
            }
            tvRankingList.setOnClickListener {
                RankingActivity.launch(requireContext())
            }

        }
        onRefresh()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun createObserve() {
        super.createObserve()
        mViewModel.apply {
            lifecycleScope.launch {
                // drop(1) 用于跳过 bannerListStateFlow 的第一次发射。通常情况下，
                // 这是为了忽略 MutableStateFlow 创建时设置的初始值（在这种情况下是一个空列表）。
                // 初始值对 UI 更新没有意义，因此跳过它以避免不必要的 UI 刷新。
                bannerListStateFlow.flowWithLifecycle(lifecycle).drop(1).collect{
                    it.let {
                        mBannerList.apply {
                            clear()
                            addAll(it)
                        }
                    }
                    mBannerAdapter.notifyDataSetChanged()
                    mBinding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }

    override fun requestError(msg: String?) {
        super.requestError(msg)
        mBinding.swipeRefreshLayout.isRefreshing = false
    }

    private fun onRefresh(){
        mBinding.swipeRefreshLayout.isRefreshing = true
        mViewModel.apply {
            fetchBanner()
        }
    }
}