package com.mrenann.chucknorris_challenge_android.view.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrenann.chucknorris_challenge_android.view.Adapters.FactsAdapter
import com.mrenann.chucknorris_challenge_android.databinding.ActivityMainBinding
import com.mrenann.chucknorris_challenge_android.model.FactsResult
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
        shimmerStop()

        viewModel = ViewModelProvider(this).get(FactsViewModel::class.java)
        setupObservables()

        binding.apply { iVsearch.setOnClickListener { searchBtn() } }
    }

    private fun setupObservables(){
        viewModel.apply {

            sucess.observe(this@MainActivity){
                binding.apply {
                    shimmerStop()
                    setupRecycler(it)
                }


            }

            error.observe(this@MainActivity) { setupErrorMsg(it) }
        }

    }

    private fun setupRecycler(factsResult: FactsResult) {
        binding.apply {
            rVfacts.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                factsResult.result?.let {  facts->
                    factsAdapter.factsList = facts
                    adapter = factsAdapter
                }
            }
        }
    }

    private fun setupErrorMsg(errorMsg : String){
        binding.tVInfo.text = errorMsg
    }

    private fun searchBtn(){
        shimmerStart()
        factsAdapter.factsList.clear()
        val palavras = mutableListOf("cou","dev","tech","god","chuck","the")
        viewModel.getFacts(palavras[Random.nextInt(0, 6)])
        factsAdapter.notifyDataSetChanged()
    }

    fun shimmerStart(){
        binding.shimmerLayout.apply {
            startShimmer()
            visibility = View.VISIBLE
        }
    }

    fun shimmerStop(){
        binding.shimmerLayout.apply {
            stopShimmer()
            visibility = View.GONE
        }
    }

}