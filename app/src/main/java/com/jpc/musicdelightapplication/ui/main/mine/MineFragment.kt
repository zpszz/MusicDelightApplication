package com.jpc.musicdelightapplication.ui.main.mine

import android.view.View.OnClickListener
import com.jpc.musicdelightapplication.R
import com.jpc.musicdelightapplication.base.BaseFragment
import com.jpc.musicdelightapplication.databinding.FragmentMineBinding

private const val TAG = "MineFragment"

/**
 * 我的 页面Fragment
 */
class MineFragment : BaseFragment<MineViewModel, FragmentMineBinding>(R.layout.fragment_mine){
    companion object {
        @JvmStatic
        fun newInstance() = MineFragment()
    }

    override fun initView() {
    }

}