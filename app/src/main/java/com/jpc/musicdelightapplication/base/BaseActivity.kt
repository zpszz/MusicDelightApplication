package com.jpc.musicdelightapplication.base

import androidx.databinding.ViewDataBinding
import com.jpc.lib_base.base.BaseVMBActivity
import com.jpc.lib_base.base.BaseViewModel

/**
 * Activity基类，继承自BaseVMBActivity
 */
abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding>(contentViewResId: Int) :
    BaseVMBActivity<VM, B>(contentViewResId) {

}