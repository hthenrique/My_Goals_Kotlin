package com.example.mygoalskotlin.Main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.mygoalskotlin.Firebase.UserFirebase
import com.example.mygoalskotlin.Login.View.LoginActivity
import com.example.mygoalskotlin.R
import com.example.mygoalskotlin.databinding.ActivityMainBinding
import com.example.mygoalskotlin.model.User
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firebaseAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    private val userFirebase: UserFirebase by lazy { UserFirebase() }
    private val user: User by lazy { User() }
    private val currentUser = getLoggedUser()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(baseContext)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, currentUser.email, Toast.LENGTH_LONG).show()

        binding.welcomeUser.text = "Welcome! " + currentUser.name

        setupButtonClick()
    }

    private fun setupButtonClick() {
        binding.editPositionButton.setOnClickListener { showOrHideEditPosition() }
        binding.editDoneButton.setOnClickListener { savePositionValue() }
        binding.cancelEditButton.setOnClickListener { showOrHideEditPosition() }

        //Goals
        binding.decreaseGoalButton.setOnClickListener {
            user.goals = decrease(user.goals)
            binding.goalsValue.text = user.goals.toString()
        }
        binding.addGoalButton.setOnClickListener {
            user.goals = increment(user.goals)
            binding.goalsValue.text = user.goals.toString()
        }

        //Matches
        binding.decreaseMatchButton.setOnClickListener {
            user.matches = decrease(user.matches)
            binding.matchesValue.text = user.matches.toString()
        }
        binding.addMatchButton.setOnClickListener {
            user.matches = increment(user.matches)
            binding.matchesValue.text = user.matches.toString() }
    }

    private fun increment(variable: Int?): Int {
        return variable!! + 1
    }

    private fun decrease(variable: Int?): Int {
        while (variable!! > 0){
            return variable - 1
        }
        return variable
    }

    private fun showOrHideEditPosition() {
        if (binding.editPositionNameLayout.visibility == View.VISIBLE){
            binding.editPositionNameLayout.visibility = View.GONE
        }else{
            binding.editPositionNameLayout.visibility = View.VISIBLE
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

    private fun getLoggedUser(): UserFirebase {
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

    private fun savePositionValue() {
        user.position = binding.editTextPosition.text.toString()
        binding.userPositionValue.text = user.position
    }
}