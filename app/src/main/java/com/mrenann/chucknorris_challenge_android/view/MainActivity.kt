package com.mrenann.chucknorris_challenge_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrenann.chucknorris_challenge_android.adapter.FactsAdapter
import com.mrenann.chucknorris_challenge_android.databinding.ActivityMainBinding
import com.mrenann.chucknorris_challenge_android.viewModel.FactsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FactsViewModel

    private val factsAdapter : FactsAdapter by lazy { FactsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(FactsViewModel::class.java)
        viewModel.getFacts("dev")
        setupRecyclerView()

        binding.apply {
            iVsearch.setOnClickListener {
                searchBtn()
            }
        }
    }

    private fun setupRecyclerView(){
        viewModel.sucess.observe(this){
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.rVfacts.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                it.result?.let {  facts->
                    factsAdapter.factsList = facts
                    adapter = factsAdapter
                }
            }
        }
    }

    private fun searchBtn(){
        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.visibility = View.VISIBLE
        factsAdapter.factsList.clear()
        factsAdapter.notifyDataSetChanged()

    }

}