package com.example.mygoalskotlin.Register.ViewModel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mygoalskotlin.Firebase.AuthListener
import com.example.mygoalskotlin.Login.View.LoginActivity
import com.example.mygoalskotlin.Register.Model.RegisterDTO
import io.reactivex.disposables.CompositeDisposable

class RegisterViewModel(): ViewModel() {

    private var email:String? = null
    private var password:String? = null

    private val registerDTO: RegisterDTO

    init {
        this.registerDTO = RegisterDTO()
    }
    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()

    fun signup(registerDTO: RegisterDTO){
        registerDTO.email = email.toString()
        registerDTO.password = password.toString()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        authListener?.onStarted()

    }

    fun goToLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}