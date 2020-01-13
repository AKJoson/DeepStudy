package com.yp.deepstudy.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yp.deepstudy.bean.HomeDataModel

class MainViewModel : ViewModel() {

    val homeDatas = MutableLiveData<List<HomeDataModel>>()
    val lists = ArrayList<HomeDataModel>()

    fun getHomeData(id: String = "123") {
        Handler().postDelayed({
            if (lists.size != 0) lists.clear()
            for (i in 1..100) {
                HomeDataModel(
                    "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1578904329&di=cc9372b6f7c622fa7c1733b63047c1d3&src=http://inews.gtimg.com/newsapp_bt/0/10932721440/641.jpg",
                    "碧罗小姐与梁三哥的爱情故事",
                    "第${i}季"
                ).let {
                    lists.add(it)
                }
            }
            homeDatas.value = lists
        }, 5000)
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.newInstance()
        }

    }
}