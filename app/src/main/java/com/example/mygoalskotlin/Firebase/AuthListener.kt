package com.example.mygoalskotlin.Firebase

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}