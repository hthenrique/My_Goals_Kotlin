package com.example.mygoalskotlin.Login.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mygoalskotlin.Firebase.FirebaseSource
import com.example.mygoalskotlin.Login.Model.LoginModel
import com.example.mygoalskotlin.Main.MainActivity
import com.example.mygoalskotlin.R
import com.example.mygoalskotlin.Register.View.RegisterActivity
import com.example.mygoalskotlin.Utils.Validator
import com.example.mygoalskotlin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginModel: LoginModel by lazy { LoginModel() }
    private val firebaseSource: FirebaseSource by lazy { FirebaseSource() }
    private val validator: Validator by lazy { Validator() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtonClicked()
    }

    private fun setupButtonClicked() {
        binding.buttonLogin.setOnClickListener {
            loginModel.email = binding.editTextEmail.text.toString().trim()
            loginModel.password = binding.editTextPassword.text.toString().trim()

            if (validator.isValidEmail(loginModel.email)){
                if (validator.isValidPassword(loginModel.password)){
                    loginUser()
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

    private fun loginUser() {
        val firebaseReturn = firebaseSource.login(loginModel)
        if (firebaseReturn){
            val loginIntent: Intent = Intent(this, MainActivity::class.java)
            startActivity(loginIntent)
            finish()
            Toast.makeText(this, firebaseSource.login(loginModel).toString(), Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Usuário inexistente", Toast.LENGTH_LONG).show()
            registerUser()
        }
    }


    private fun registerUser(){
        val registerIntent: Intent = Intent(this, RegisterActivity::class.java)
        registerIntent.putExtra("email", binding.editTextEmail.text.toString().trim())
        startActivity(registerIntent)
    }
}