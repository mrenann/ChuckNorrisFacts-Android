package com.mrenann.chucknorris_challenge_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrenann.chucknorris_challenge_android.R
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

        viewModel = ViewModelProvider(this).get(FactsViewModel::class.java)

        setupRecyclerView()

        binding.apply {
            iVsearch.setOnClickListener {
                searchBtn()
            }
        }
    }

    private fun setupRecyclerView(){
        viewModel.sucess.observe(this){
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
        viewModel.getFacts("dev")
    }



}