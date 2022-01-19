package com.example.mygoalskotlin.Login.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygoalskotlin.Firebase.UserRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val userRepository: UserRepository)
    : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(userRepository) as T
    }
}