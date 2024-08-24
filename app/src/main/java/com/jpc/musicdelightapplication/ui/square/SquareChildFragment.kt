package com.jpc.musicdelightapplication.ui.square

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jpc.musicdelightapplication.R
import com.jpc.musicdelightapplication.base.BaseFragment
import com.jpc.musicdelightapplication.databinding.IncludeSwiperefreshRecyclerviewBinding

class SquareChildFragment: BaseFragment<SquareChildViewModel, IncludeSwiperefreshRecyclerviewBinding>(
    R.layout.include_swiperefresh_recyclerview
) {

    companion object{
        private const val TAB_TITLE = "tab_title"

        fun newInstance(tabTitle: String): Fragment{
            return SquareChildFragment().apply {
                arguments = Bundle().apply {
                    putString(TAB_TITLE, tabTitle)
                }
            }
        }
    }

    override fun initView() {

    }
}