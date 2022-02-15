package com.example.mygoalskotlin.Login.ViewModel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mygoalskotlin.Firebase.AuthListener
import com.example.mygoalskotlin.Login.Model.LoginDTO
import com.example.mygoalskotlin.Register.View.RegisterActivity
import io.reactivex.disposables.CompositeDisposable


class LoginViewModel(): ViewModel() {

    private var email:String? = null
    private var password:String? = null

    private val loginDTO: LoginDTO

    init {
        this.loginDTO = LoginDTO()
    }

    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()

    fun login(loginDTO: LoginDTO){
        if (loginDTO.email.isEmpty() || loginDTO.password.isEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        authListener?.onStarted()

    }

    fun goToSignup(view: View){
        Intent(view.context, RegisterActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}