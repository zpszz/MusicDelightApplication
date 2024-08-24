package com.jpc.musicdelightapplication.ui.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jpc.lib_base.utils.ToastUtil
import com.jpc.musicapp.ext.clearLongClickToast
import com.jpc.musicdelightapplication.R
import com.jpc.musicdelightapplication.base.BaseActivity
import com.jpc.musicdelightapplication.databinding.ActivityMainBinding

/**
 * 主Activity
 */
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.apply {
            //val navController = findNavController(R.id.nav_host_fragment_activity_main)
            val navHostController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
            val navController = navHostController!!.findNavController()
            // 这是一个扩展函数，用于将BottomNavigationView与NavController关联
            navView.setupWithNavController(navController)

            navView.apply {
                clearLongClickToast(
                    arrayListOf(
                        R.id.navigation_home,
                        R.id.navigation_discover,
                        R.id.navigation_mine
                    )
                )
            }
            // 设置Drawer关联Toolbar
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            // 在toolbar添加搜索按钮
            toolbar.inflateMenu(R.menu.menu_main)
        }
        // 返回键处理
        onBackPressedDispatcher.addCallback(this, mBackPressedCallback)
    }

    // 记录上一次点击返回按钮的时间
    private var lastBackMills: Long = 0

    // 返回监听回调
    private val mBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - lastBackMills > 2000) {
                lastBackMills = System.currentTimeMillis()
                ToastUtil.showShort(this@MainActivity, getString(R.string.toast_double_back_exit))
            } else {
                finish()
            }
        }
    }
}