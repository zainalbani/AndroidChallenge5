package com.example.challenge5.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.challenge5.R
import com.example.challenge5.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {
    private var _binding : ActivityDetailMovieBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}