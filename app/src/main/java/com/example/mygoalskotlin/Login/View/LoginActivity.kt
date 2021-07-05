package com.example.mygoalskotlin.Login.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mygoalskotlin.Login.Model.LoginModel
import com.example.mygoalskotlin.Login.ViewModel.LoginViewModel
import com.example.mygoalskotlin.R

class LoginActivity : AppCompatActivity() {
    private var loginModel = LoginModel()
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var editTextEmail: Button
    private lateinit var editTextPassword: Button
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)


        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        setupInputChangedListener()
        setupObservers()
    }

    private fun setupObservers() {
        loginViewModel.onUserSendForm.observe(this, Observer {

        })
    }

    private fun setupButtonClickedListener(){
        buttonLogin.setOnClickListener {
            loginViewModel.onUserRequestedToSendForm()
        }
    }

    private fun setupInputChangedListener() {
        loginModel.email = editTextEmail.toString().trim()
        loginModel.password = editTextPassword.toString().trim()
        editTextEmail.addTextChangedListener {
            loginViewModel.onUserChangedInput(loginModel)
        }
    }
}