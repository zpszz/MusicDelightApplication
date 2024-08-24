package com.jpc.musicdelightapplication.ui.ranking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jpc.musicdelightapplication.R
import com.jpc.musicdelightapplication.base.BaseActivity
import com.jpc.musicdelightapplication.databinding.ActivityRankingBinding

class RankingActivity: BaseActivity<RankingViewModel, ActivityRankingBinding>(R.layout.activity_ranking){
    companion object{
        fun launch(context: Context){
            context.startActivity(Intent(context, RankingActivity::class.java))
        }
    }
    override fun initView(savedInstanceState: Bundle?) {

    }
}