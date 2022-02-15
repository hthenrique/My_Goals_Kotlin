package com.example.mygoalskotlin.Login.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mygoalskotlin.Login.Model.LoginDTO
import com.example.mygoalskotlin.Main.MainActivity
import com.example.mygoalskotlin.R
import com.example.mygoalskotlin.Register.View.RegisterActivity
import com.example.mygoalskotlin.Utils.MessagesConstants
import com.example.mygoalskotlin.Utils.Validator
import com.example.mygoalskotlin.databinding.ActivityLoginBinding
import com.example.mygoalskotlin.model.UserDTO
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var mockEmail = "henrique@gmail.com"
    private var mockpassword = "Henrique#3"

    private val loginDTO: LoginDTO by lazy { LoginDTO() }
    private val validator: Validator by lazy { Validator() }
    private var firebaseAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        firebaseAuth = FirebaseAuth.getInstance()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextEmail.setText(mockEmail)
        binding.editTextPassword.setText(mockpassword)

        setupButtonClicked()
    }

    private fun setupButtonClicked() {
        binding.buttonLogin.setOnClickListener {

            loginDTO.email = binding.editTextEmail.text.toString().trim()
            loginDTO.password = binding.editTextPassword.text.toString().trim()

            if (validator.isValidEmail(loginDTO.email)){
                if (validator.isValidPassword(loginDTO.password)){
                    firebaseRequest()
                }else{
                    binding.editTextPassword.error = getString(R.string.password_Requirements)
                    Toast.makeText(this, MessagesConstants.INVALID_PASSWORD, Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, MessagesConstants.INVALID_EMAIL, Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonRegister.setOnClickListener {
            registerUser()
        }

    }

    private fun firebaseRequest(){
        firebaseAuth?.signInWithEmailAndPassword(loginDTO.email, loginDTO.password)
            ?.addOnCompleteListener {
                if (it.isSuccessful){
                    loginUser()
                    saveUserInSharedPrefs()
                }else{
                    Toast.makeText(this,MessagesConstants.NON_EXISTENT_USER,Toast.LENGTH_LONG).show()
                    registerUser()
                }
            }
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveUserInSharedPrefs() {
        val userDTOToSave: UserDTO = UserDTO()

        userDTOToSave.email = binding.editTextEmail.text.toString()
        userDTOToSave.password = binding.editTextPassword.text.toString()

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserSaved", Context.MODE_PRIVATE)
        val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
        prefsEditor.putBoolean("isUserLogin", true)
        prefsEditor.putString("email", userDTOToSave.email)
        prefsEditor.putString("password", userDTOToSave.password)
        prefsEditor.apply()
        prefsEditor.commit()

    }

    private fun loginUser() {
        val loginIntent: Intent = Intent(this, MainActivity::class.java)
        startActivity(loginIntent)
        finish()
    }

    private fun registerUser(){
        val registerIntent: Intent = Intent(this, RegisterActivity::class.java)
        registerIntent.putExtra("email", binding.editTextEmail.text.toString().trim())
        startActivity(registerIntent)
    }
}