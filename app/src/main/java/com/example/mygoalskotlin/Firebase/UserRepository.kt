package com.example.mygoalskotlin.Firebase

import com.example.mygoalskotlin.Login.Model.LoginModel
import com.example.mygoalskotlin.Register.Model.RegisterModel
import com.google.firebase.auth.FirebaseAuth

class UserRepository(private val firebase: FirebaseSource) {

    fun login() {}
    fun register(registerModel: RegisterModel) = firebase.register(registerModel)
    fun currentUser() = firebase.currentUser()
    fun logout() = firebase.logout()

}