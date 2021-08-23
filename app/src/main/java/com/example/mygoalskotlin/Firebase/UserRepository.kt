package com.example.mygoalskotlin.Firebase

import com.example.mygoalskotlin.Login.Model.Login
import com.example.mygoalskotlin.Register.Model.RegisterModel

class UserRepository(private val firebase: FirebaseSource) {

    fun login(login: Login) = firebase.login(login)
    fun register(registerModel: RegisterModel) = firebase.register(registerModel)
    fun currentUser() = firebase.currentUser()
    fun logout() = firebase.logout()

}