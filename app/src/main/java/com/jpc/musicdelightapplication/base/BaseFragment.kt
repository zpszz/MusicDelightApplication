package com.jpc.musicdelightapplication.base

import androidx.databinding.ViewDataBinding
import com.jpc.lib_base.base.BaseVMBFragment
import com.jpc.lib_base.base.BaseViewModel

/**
 * 继承自BaseVMBFragment
 * 在这里只做了统一处理跳转登录页面的逻辑
 */
abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding>(contentViewResId: Int) :
    BaseVMBFragment<VM, B>(contentViewResId) {

//    override fun createObserve() {
//        super.createObserve()
//        mViewModel.errorResponse.observe(viewLifecycleOwner) {
//            if (it?.errorCode == -1001) { // 需要登录
//                LoginActivity.launch(requireContext())
//            }
//        }
//    }
}