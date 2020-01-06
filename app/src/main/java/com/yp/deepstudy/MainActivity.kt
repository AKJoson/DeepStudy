package com.yp.deepstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yp.deepstudy.adapter.MainRecyclerAdapter
import com.yp.deepstudy.databinding.ActivityMainBinding
import com.yp.deepstudy.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dataLists = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val viewModel =
            ViewModelProvider(this, MainViewModel.Factory()).get(MainViewModel::class.java)
        val adapter = MainRecyclerAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dataLists.add("Take Photo")
        dataLists.add("Gallely")
        adapter.submitList(dataLists)
        setContentView(binding.root)
    }
}
