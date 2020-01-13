package com.yp.deepstudy.adapter

/**
 * Too many times, the type of Item is similar.
 * you can use this adapter.
 */
abstract class BaseAdapter<T>(private val layoutId: Int) : MyRecyclerAdapter<T>() {
    override fun getLayoutIdByViewType(viewType: Int): Int {
        return layoutId
    }
}