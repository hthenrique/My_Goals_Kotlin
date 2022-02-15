package com.example.mygoalskotlin.Main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.mygoalskotlin.Firebase.UserFirebaseDTO
import com.example.mygoalskotlin.Login.View.LoginActivity
import com.example.mygoalskotlin.R
import com.example.mygoalskotlin.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firebaseAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    private val currentUser = getLoggedUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(baseContext)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, currentUser.email, Toast.LENGTH_LONG).show()
        binding.helloWorld.text = currentUser.email

        binding.getUserButton.setOnClickListener {
            Toast.makeText(this, currentUser.email, Toast.LENGTH_LONG).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.logoutUser -> {
                logoutUser()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    private fun logoutUser() {
        Firebase.auth.signOut()
        deleteUserFromSharedPrefs()
        backToLogin()
    }

    private fun getLoggedUser(): UserFirebaseDTO {
        val userFirebase = UserFirebaseDTO()
        val firebaseUser = Firebase.auth.currentUser

        if (firebaseUser!= null){
            userFirebase.uid = firebaseUser.uid
            userFirebase.name = firebaseUser.displayName
            userFirebase.email = firebaseUser.email
            userFirebase.isAuthenticated = true
            userFirebase.isCreated = true
        }
        return userFirebase
    }

    private fun deleteUserFromSharedPrefs() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserSaved", Context.MODE_PRIVATE)
        val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
        prefsEditor.remove("isUserLogin")
        prefsEditor.apply()
        prefsEditor.commit()
    }

    private fun backToLogin() {
        val startLogin: Intent = Intent(this, LoginActivity::class.java)
        startActivity(startLogin)
        finish()
    }
}