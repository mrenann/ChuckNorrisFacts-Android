package com.mrenann.chucknorris_challenge_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrenann.chucknorris_challenge_android.R
import com.mrenann.chucknorris_challenge_android.databinding.ActivityMainBinding
import com.mrenann.chucknorris_challenge_android.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}