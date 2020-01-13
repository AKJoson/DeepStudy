package com.yp.deepstudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yp.deepstudy.R
import com.yp.deepstudy.databinding.ItemMainRecyclerViewBinding

/**
 * You can use this Adapter , extends ListAdapter<T,ViewHolder>(DiffUtilItemCallBack())
 * But something will happen in next , when you need show another RecyclerView , you must
 * rewirte onCreateViewHolder() onBindViewHolder().. , or Think one thing, This recyclerView include too many different type of item ..
 * you must write too many code which is similar...
 * So What should I do ??
 * maybe you should write a BaseAdapter. you will not need to rewrite OnCreateViewHolder , OnBindViewHolder..
 *
 * So Let's Do it!
 * @see MyRecyclerAdapter
 */
class MainRecyclerAdapter :
        ListAdapter<String, MainRecyclerAdapter.ViewHolder>(MainDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_main_recycler_view,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemMainRecyclerViewBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String?) {
            binding.value = item
            binding.executePendingBindings()
        }
    }

}

class MainDiffUtilCallBack : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem?.equals(newItem)
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem?.equals(newItem)
    }

}