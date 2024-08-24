package com.jpc.musicdelightapplication.ui.main.discover

import com.jpc.musicdelightapplication.R
import com.jpc.musicdelightapplication.base.BaseFragment
import com.jpc.musicdelightapplication.databinding.FragmentDiscoverBinding

private const val TAG = "DiscoverFragment"

/**
 * 发现页Fragment
 */
class DiscoverFragment : BaseFragment<DiscoverViewModel, FragmentDiscoverBinding>(R.layout.fragment_discover) {
    companion object {
        @JvmStatic
        fun newInstance() = DiscoverFragment()
    }

    override fun initView() {

    }
}