package com.example.mygoalskotlin.Register.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mygoalskotlin.Firebase.FirebaseSource
import com.example.mygoalskotlin.Register.Model.RegisterModel
import com.example.mygoalskotlin.Utils.Validator
import com.example.mygoalskotlin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val firebaseSource: FirebaseSource by lazy { FirebaseSource() }
    private val validator: Validator by lazy { Validator() }
    private val registerModel: RegisterModel by lazy { RegisterModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = getIntent()
        if (intent != null){
            registerModel.email = intent.getStringExtra("email").toString()
            binding.editTextRegisterEmail.setText(registerModel.email)
        }

        setupButtonRegister()
    }

    private fun setupButtonRegister() {

        binding.buttonCreateUser.setOnClickListener {
            registerModel.email = binding.editTextRegisterEmail.text.toString().trim()
            registerModel.password = binding.editTextPassword.text.toString().trim()
            registerModel.confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            if (validator.isValidEmail(registerModel.email)){
                if (registerModel.password == registerModel.confirmPassword){
                    if (validator.isValidPassword(registerModel.password)){
                        isValidUser(true)
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

    private fun isValidUser(valid: Boolean){
        if (valid){
            firebaseSource.register(registerModel)
            onBackPressed()
        }
    }
}