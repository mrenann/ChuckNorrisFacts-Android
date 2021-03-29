package com.mrenann.chucknorris_challenge_android.view.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrenann.chucknorris_challenge_android.view.Adapters.FactsAdapter
import com.mrenann.chucknorris_challenge_android.databinding.ActivityMainBinding
import com.mrenann.chucknorris_challenge_android.model.FactsResult
import com.mrenann.chucknorris_challenge_android.viewModel.FactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: FactsViewModel by viewModel()


    private val factsAdapter : FactsAdapter by lazy { FactsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shimmerStop()

        setupObservables()
        setupSearchView()
    }

    private fun setupObservables(){
        viewModel.apply {

            sucess.observe(this@MainActivity){
                binding.apply {
                    shimmerStop()
                    setupRecycler(it)
                }
            }

            error.observe(this@MainActivity) { setupMSG(it) }
        }

    }

    private fun setupRecycler(factsResult: FactsResult) {
        binding.apply {
            rVfacts.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                factsResult.result?.let {  facts->
                    if(facts.isEmpty()) setupMSG("Data not Found") else{
                        factsAdapter.factsList = facts
                        adapter = factsAdapter
                    }
                }
            }
        }
    }

    private fun setupMSG(msg : String){
        binding.apply {
            shimmerStop()
            infoImg.visibility = View.VISIBLE
            infoTxt.visibility = View.VISIBLE
            infoTxt.text = msg
        }
    }

    private fun searchBtn(word: String){
        binding.apply {
            infoImg.visibility = View.GONE
            infoTxt.visibility = View.GONE
        }
        shimmerStart()
        factsAdapter.factsList.clear()
        viewModel.getFacts(word)
        factsAdapter.notifyDataSetChanged()
    }

    private fun shimmerStart(){
        binding.shimmerLayout.apply {
            startShimmer()
            visibility = View.VISIBLE
        }
    }

    private fun shimmerStop(){
        binding.shimmerLayout.apply {
            stopShimmer()
            visibility = View.GONE
        }
    }

    private fun setupSearchView(){
        binding.apply {
            searchV.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {word->
                        if(word.length in 3..119) searchBtn(word)
                        else setupMSG("Query must be more than 3 chars and less than 120 chars")
                    }
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean = true })
        }
    }
}