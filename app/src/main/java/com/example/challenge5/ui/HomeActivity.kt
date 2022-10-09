package com.example.challenge5.ui

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.challenge5.R
import com.example.challenge5.preference.PrefManager
import com.example.challenge5.databinding.ActivityHomeBinding
import kotlin.math.log

class HomeActivity : AppCompatActivity() {
    private var _binding : ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefManager: PrefManager
    private lateinit var email : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager(this)
        email = prefManager.getEmail().toString()
        checkLogin()
        setupUI()

        binding.btnLogout.setOnClickListener {
            prefManager.removeData()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        showFragment()
    }

    private fun setupUI() {
        binding.tvData.text = "Welcome $email"
        Log.d(TAG, "setupUI: $email")
    }

    private fun showFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = DataFragment()

        mFragmentTransaction.add(R.id.flData,mFragment).commit()
    }

    private fun checkLogin() {
        if (prefManager.isLogin() == false){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}