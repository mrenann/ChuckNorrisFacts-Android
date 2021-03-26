package com.mrenann.chucknorris_challenge_android.view.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrenann.chucknorris_challenge_android.view.Adapters.FactsAdapter
import com.mrenann.chucknorris_challenge_android.databinding.ActivityMainBinding
import com.mrenann.chucknorris_challenge_android.viewModel.FactsViewModel
import kotlin.random.Random

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
        setupRecyclerView()

        binding.apply {
            iVsearch.setOnClickListener {
                searchBtn()
            }
        }
    }

    private fun setupRecyclerView(){
        viewModel.apply {

            sucess.observe(this@MainActivity){
                binding.apply {
                    shimmerLayout.stopShimmer()
                    shimmerLayout.visibility = View.GONE
                    rVfacts.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                        it.result?.let {  facts->
                            factsAdapter.factsList = facts
                            adapter = factsAdapter
                        }
                    }
                }

            }

            error.observe(this@MainActivity){
                binding.tVInfo.text = it
            }
        }

    }

    private fun searchBtn(){
        binding.shimmerLayout.apply {
            startShimmer()
            visibility = View.VISIBLE
        }

        factsAdapter.factsList.clear()
        val palavras = mutableListOf("de","te","go")
        viewModel.getFacts(palavras[Random.nextInt(0, 3)])
        factsAdapter.notifyDataSetChanged()

    }

}