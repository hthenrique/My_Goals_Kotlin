package com.example.mygoalskotlin.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mygoalskotlin.Firebase.FirebaseSource
import com.example.mygoalskotlin.R
import com.example.mygoalskotlin.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firebaseSource: FirebaseSource = FirebaseSource()
    private var firebaseAuth: FirebaseAuth? = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        getLoggedUser()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = firebaseSource.currentUser()
        binding.helloWorld.text = currentUser.name
    }

    private fun getLoggedUser() {

    }
}