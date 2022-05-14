package com.example.mygoalskotlin.firebase

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}