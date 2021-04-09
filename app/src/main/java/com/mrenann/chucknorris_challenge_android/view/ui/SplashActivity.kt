package com.mrenann.chucknorris_challenge_android.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrenann.chucknorris_challenge_android.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}