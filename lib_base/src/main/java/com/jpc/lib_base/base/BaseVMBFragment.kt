package com.jpc.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jpc.lib_base.BR
import com.jpc.lib_base.R
import com.jpc.lib_base.ext.hideLoading
import com.jpc.lib_base.utils.LogUtil
import com.jpc.lib_base.utils.ToastUtil
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * 封装了ViewModel和DataBinding的Fragment基类
 * @param VM ViewModel的子类
 * @param B ViewDataBinding的子类
 * @param contentViewResId 布局文件id
 */
abstract class BaseVMBFragment<VM : BaseViewModel, B : ViewDataBinding>(private val contentViewResId: Int) :
    Fragment() {

    // 是否第一次加载
    private var mIsFirstLoading = true

    protected lateinit var mViewModel: VM
    protected lateinit var mBinding: B

    /**
     * 创建视图
     * @param inflater LayoutInflater 布局加载器
     * @param container ViewGroup? 容器
     * @param savedInstanceState Bundle? 保存的实例状态
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, contentViewResId, container, false)
        return mBinding.root
    }

    /**
     * 视图创建完成
     * @param view View 视图
     * @param savedInstanceState Bundle? 保存的实例状态
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mIsFirstLoading = true
        initViewModel()
        initView()
        setupDataBinding()
        createObserve()
    }

    // ViewModel初始化
    @Suppress("UNCHECKED_CAST")
    private fun initViewModel() {
        // 这里利用反射获取泛型中第一个参数ViewModel
        val type: Class<VM> =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        mViewModel = ViewModelProvider(this)[type]
        mViewModel.start()
    }

    // DataBinding相关设置
    private fun setupDataBinding() {
        mBinding.apply {
            // 需绑定lifecycleOwner到Fragment,xml绑定的数据才会随着liveData数据源的改变而改变
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, mViewModel)
        }
    }

    //View相关初始化
    abstract fun initView()

    /**
     * 恢复，当Fragment可见时调用
     */
    override fun onResume() {
        super.onResume()
        if (mIsFirstLoading) {
            // 如果是第一次加载，调用懒加载方法
            lazyLoadData()
            mIsFirstLoading = false
        }
    }

    // 数据懒加载
    open fun lazyLoadData() {}

    // 提供编写LiveData监听逻辑的方法
    open fun createObserve() {     // 全局服务器请求错误监听
        mViewModel.apply {
            exception.observe(viewLifecycleOwner) {
                requestError(it.message)
                LogUtil.e("网络请求错误：${it.message}")
                when (it) {
                    is SocketTimeoutException -> ToastUtil.showShort(
                        requireContext(),
                        getString(R.string.request_time_out)
                    )

                    is ConnectException, is UnknownHostException -> ToastUtil.showShort(
                        requireContext(),
                        getString(R.string.network_error)
                    )

                    else -> ToastUtil.showShort(
                        requireContext(), it.message ?: getString(R.string.response_error)
                    )
                }
            }

            // 全局服务器返回的错误信息监听
            errorResponse.observe(viewLifecycleOwner) {
                requestError(it?.message)
                it?.message?.run {
                    ToastUtil.showShort(requireContext(), this)
                }
            }
        }
    }

    // 提供一个请求错误的方法,用于像关闭加载框之类的
    open fun requestError(msg: String? = null) {
        hideLoading()
    }
}