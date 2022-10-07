package com.example.challenge5.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.challenge5.utils.sql.DatabaseHelper
import com.example.challenge5.utils.sql.InputValidation
import com.example.challenge5.preference.PrefManager
import com.example.challenge5.R
import com.example.challenge5.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        initListeners()
        initObjects()
        checkLogin()
    }
    private fun initListeners() {
        binding.btnLogin!!.setOnClickListener(this)
        binding.textViewLinkRegister!!.setOnClickListener(this)
    }
    private fun initObjects() {
        databaseHelper = DatabaseHelper(this)
        inputValidation = InputValidation(this)
        prefManager= PrefManager(this)
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLogin -> verifyFromSQLite()
            R.id.textViewLinkRegister -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }
    private fun verifyFromSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(binding.textInputEditTextEmail!!, binding.textInputLayoutEmail!!, getString(
                R.string.error_message_email
            ))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(binding.textInputEditTextEmail!!, binding.textInputLayoutEmail!!, getString(
                R.string.error_message_email
            ))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(binding.textInputEditTextPassword!!, binding.textInputLayoutPassword!!, getString(
                R.string.error_message_email
            ))) {
            return
        }
        if (databaseHelper!!.checkUser(binding.textInputEditTextEmail!!.text.toString().trim { it <= ' ' }, binding.textInputEditTextPassword!!.text.toString().trim { it <= ' ' })) {
            prefManager.setLogin(true)
            prefManager.setEmail(binding.textInputEditTextEmail.toString().trim())
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("EMAIL", binding.textInputEditTextEmail!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(intent)
        } else {
            Snackbar.make(binding.constraintLayout!!,getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun emptyInputEditText() {
        binding.textInputEditTextEmail!!.text = null
        binding.textInputEditTextPassword!!.text = null
    }
    private fun checkLogin(){
        if(prefManager.isLogin()!!){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}