package com.yp.deepstudy

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yp.deepstudy.adapter.BaseAdapter
import com.yp.deepstudy.adapter.MainRecyclerAdapter
import com.yp.deepstudy.bean.HomeDataModel
import com.yp.deepstudy.databinding.ActivityMainBinding
import com.yp.deepstudy.databinding.ItemNewRecyclerViewBinding
import com.yp.deepstudy.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dataLists = ArrayList<HomeDataModel>()

    val progressDialog by lazy {
        ProgressDialog.show(this, "", "loading")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel =
            ViewModelProvider(this, MainViewModel.Factory()).get(MainViewModel::class.java)
        val adapter =
            object : BaseAdapter<ItemNewRecyclerViewBinding>(R.layout.item_new_recycler_view) {

                override fun bindEvent(
                    binding: ItemNewRecyclerViewBinding,
                    position: Int,
                    any: Any
                ) {
                    binding.iv1.setOnClickListener {
                        Toast.makeText(
                            this@MainActivity,
                            "$position ${(any as HomeDataModel).url}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun getItemDataByPosition(position: Int): Any {
                    return dataLists[position]
                }

                override fun getItemCount(): Int {
                    return dataLists.size
                }
            }
        binding.recyclerView.apply {
            this.adapter = adapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
        subscribeUi(binding, adapter, viewModel)
        getHomeData(viewModel)
    }

    private fun subscribeUi(
        binding: ActivityMainBinding?,
        adapter: BaseAdapter<ItemNewRecyclerViewBinding>,
        viewModel: MainViewModel
    ) {
        viewModel.homeDatas.observe(this, Observer {
            it?.let {
                progressDialog.cancel()
                dataLists.clear()
                dataLists.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun getHomeData(viewModel: MainViewModel) {
        progressDialog.show()
        viewModel.getHomeData()
    }


}
