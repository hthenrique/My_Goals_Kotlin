package com.example.mygoalskotlin.Firebase

import com.example.mygoalskotlin.Login.Model.LoginModel
import com.example.mygoalskotlin.Register.Model.RegisterModel

class UserRepository(private val firebase: FirebaseSource) {

    fun login(loginModel: LoginModel) = firebase.login(loginModel)
    fun register(registerModel: RegisterModel) = firebase.register(registerModel)
    fun currentUser() = firebase.currentUser()
    fun logout() = firebase.logout()

}