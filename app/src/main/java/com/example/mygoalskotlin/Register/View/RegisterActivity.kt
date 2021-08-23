package com.example.mygoalskotlin.Register.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mygoalskotlin.Firebase.FirebaseSource
import com.example.mygoalskotlin.Register.Model.RegisterModel
import com.example.mygoalskotlin.Utils.Controller
import com.example.mygoalskotlin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val firebaseSource: FirebaseSource by lazy { FirebaseSource() }
    private val controller: Controller by lazy { Controller() }
    private val registerModel: RegisterModel by lazy { RegisterModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = getIntent()
        registerModel.email = intent.getStringExtra("email").toString()
        binding.editTextRegisterEmail.setText(registerModel.email)
        setupButtonRegister()
    }

    private fun setupButtonRegister() {
        binding.buttonCreateUser.setOnClickListener {
            if (controller.isValidEmail(binding.editTextRegisterEmail.text.toString().trim())){
                registerModel.email = binding.editTextRegisterEmail.text.toString().trim()
                if (binding.editTextRegisterPassword.text.toString().trim().equals(
                        binding.editTextConfirmPassword.text.toString().trim())){
                    if (controller.isValidPassword(binding.editTextRegisterPassword.text.toString().trim())){
                        registerModel.password = binding.editTextRegisterPassword.text.toString().trim()
                    }else{
                        Toast.makeText(this, "Senha inválida", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this, "Senhas diferentes", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Email inválido", Toast.LENGTH_LONG).show()
            }
        }
    }
}