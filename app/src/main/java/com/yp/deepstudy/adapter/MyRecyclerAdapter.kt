package com.yp.deepstudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yp.deepstudy.BR

/**
 * okay,As you can see, this adapter is easy to use .
 * because use dataBinding,So findViewById \ TextView \ ImageViewView is does't need you to write.
 *
 * Thinking: some recyclerView only have one type of item,
 * So you need't rewrite getLayoutIdByViewType().
 * so, create one BaseAdapter to make it easy.
 * @see BaseAdapter
 */

abstract class MyRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getLayoutIdByViewType(viewType),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val anyData = getItemDataByPosition(position)
        holder.binding.setVariable(BR.any, anyData)
        bindEvent(holder.binding as T, position, anyData)
        holder.binding.executePendingBindings()
    }

    abstract fun getLayoutIdByViewType(viewType: Int): Int

    abstract fun getItemDataByPosition(position: Int): Any

    abstract fun bindEvent(binding: T, position: Int, any: Any)

}