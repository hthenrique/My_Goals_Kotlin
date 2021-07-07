package com.example.mygoalskotlin.Login.View

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mygoalskotlin.Login.Model.LoginModel
import com.example.mygoalskotlin.Login.ViewModel.LoginViewModel
import com.example.mygoalskotlin.R

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private var loginViewModel: LoginViewModel? = null

    private val loginModel: LoginModel by lazy { LoginModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        setupLoginButtonClicked()
    }

    private fun setupLoginButtonClicked() {
        buttonLogin.setOnClickListener {
            loginModel.email = editTextEmail.text.toString().trim()
            loginModel.password = editTextPassword.text.toString().trim()
            this.loginViewModel?.login(loginModel)
            Toast.makeText(this, loginModel.email, Toast.LENGTH_LONG).show()
        }
    }
}