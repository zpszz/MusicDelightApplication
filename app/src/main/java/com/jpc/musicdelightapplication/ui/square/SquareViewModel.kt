package com.jpc.musicdelightapplication.ui.square

import androidx.lifecycle.MutableLiveData
import com.jpc.lib_base.base.BaseViewModel
import com.jpc.lib_base.ext.handleRequest
import com.jpc.lib_base.ext.launch
import com.jpc.musicdelightapplication.data.DataRepository
import com.jpc.musicdelightapplication.data.bean.PlaylistTagData

class SquareViewModel: BaseViewModel(){
    val playlistTagLiveData = MutableLiveData<List<PlaylistTagData>?>()
    override fun start() {
        // 立即调用一次
        fetchPlaylistTag()
    }

    private fun fetchPlaylistTag(){
        launch({
            handleRequest(
                DataRepository.getPlaylistTagList(),{ playlistTagLiveData.value = it.data }
            )
        })
    }
}