package com.example.mygoalskotlin.Login.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mygoalskotlin.Firebase.FirebaseSource
import com.example.mygoalskotlin.Login.Model.Login
import com.example.mygoalskotlin.R
import com.example.mygoalskotlin.Register.View.RegisterActivity
import com.example.mygoalskotlin.Utils.Controller
import com.example.mygoalskotlin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val login: Login by lazy { Login() }
    private val firebaseSource: FirebaseSource by lazy { FirebaseSource() }
    private val controller: Controller by lazy { Controller() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtonClicked()
    }

    private fun setupButtonClicked() {
        binding.buttonLogin.setOnClickListener {
            if (controller.isValidEmail(binding.editTextEmail.text.toString().trim())){
                login.email = binding.editTextEmail.text.toString().trim()
                if (controller.isValidPassword(binding.editTextPassword.text.toString().trim())){
                    login.password = binding.editTextPassword.text.toString().trim()
                    if (firebaseSource.login(login)){
                        Toast.makeText(this, firebaseSource.login(login).toString(), Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "Usuário inexistente", Toast.LENGTH_LONG).show()
                        registerUser()
                    }
                }else{
                    binding.editTextPassword.error = getString(R.string.password_Requirements)
                    Toast.makeText(this, "Senha inválida", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Email inválido", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonRegister.setOnClickListener {
            registerUser()
        }

    }


    private fun registerUser(){
        val registerIntent: Intent = Intent(this, RegisterActivity::class.java)
        registerIntent.putExtra("email", binding.editTextEmail.text.toString().trim())
        startActivity(registerIntent)
    }
}