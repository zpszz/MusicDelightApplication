package com.jpc.musicdelightapplication.widget

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.core.text.buildSpannedString
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.jpc.musicdelightapplication.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * 自定义播放栏
 */
