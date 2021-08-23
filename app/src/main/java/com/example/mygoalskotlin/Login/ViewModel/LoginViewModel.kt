package com.example.mygoalskotlin.Login.ViewModel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mygoalskotlin.Firebase.AuthListener
import com.example.mygoalskotlin.Firebase.UserRepository
import com.example.mygoalskotlin.Login.Model.Login
import com.example.mygoalskotlin.Register.View.RegisterActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    private var email:String? = null
    private var password:String? = null

    private val login: Login

    init {
        this.login = Login()
    }

    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()
    val user by lazy { userRepository.currentUser() }

    fun login(login: Login){
        if (login.email.isEmpty() || login.password.isEmpty()){
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