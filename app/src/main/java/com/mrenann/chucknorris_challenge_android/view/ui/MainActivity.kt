package com.mrenann.chucknorris_challenge_android.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrenann.chucknorris_challenge_android.databinding.ActivityMainBinding
import com.mrenann.chucknorris_challenge_android.model.Fact
import com.mrenann.chucknorris_challenge_android.view.adapters.FactsAdapter
import com.mrenann.chucknorris_challenge_android.viewModel.FactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: FactsViewModel by viewModel()

    private val factsAdapter: FactsAdapter by lazy { FactsAdapter({ fact->
        fact?.let {fato-> shareLink(fato) }
    }) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadAPIData()
        initRecycler()
        setupSearchView()
    }

    private fun loadAPIData() {
        viewModel.apply {
            factsList.observe(this@MainActivity, {
                it?.let {
                    it.result?.let { facts ->
                        factsAdapter.factsList = facts
                        factsAdapter.notifyDataSetChanged()

                        changeVisibilityInfos(View.GONE)
                    }
                }
                shimmerStop()
            })
            errorList.observe(this@MainActivity) { setupMSG(it) }
        }
    }

    private fun initRecycler() {
        binding.apply {
            rVfacts.apply {
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                adapter = factsAdapter
            }
        }
    }

    private fun setupMSG(msg: String) {
        binding.apply {
            shimmerStop()
            factsAdapter.factsList.clear()
            factsAdapter.notifyDataSetChanged()

            changeVisibilityInfos(View.VISIBLE)

            infoTxt.text = msg
        }
    }

    private fun changeVisibilityInfos(visibility: Int){
        binding.apply {
            infoImg.visibility = visibility
            infoTxt.visibility = visibility
        }
    }

    private fun searchBtn(word: String) {
        changeVisibilityInfos(View.GONE)

        shimmerStart()
        factsAdapter.factsList.clear()
        viewModel.getFacts(word)
        factsAdapter.notifyDataSetChanged()
    }

    private fun shimmerStart() {
        binding.shimmerLayout.apply {
            startShimmer()
            visibility = View.VISIBLE
        }
    }

    private fun shimmerStop() {
        binding.shimmerLayout.apply {
            stopShimmer()
            visibility = View.GONE
        }
    }

    private fun setupSearchView() {
        binding.apply {
            searchV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { word -> searchBtn(word) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = true
            })
        }
    }

    private fun shareLink(fact: Fact) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${fact.url}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "${fact.value}")
        startActivity(shareIntent)
    }
}