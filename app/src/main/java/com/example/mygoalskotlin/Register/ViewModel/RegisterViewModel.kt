package com.example.mygoalskotlin.Register.ViewModel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mygoalskotlin.Firebase.AuthListener
import com.example.mygoalskotlin.Firebase.UserRepository
import com.example.mygoalskotlin.Login.View.LoginActivity
import com.example.mygoalskotlin.Register.Model.RegisterModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(private val userRepository: UserRepository): ViewModel() {

    private var email:String? = null
    private var password:String? = null

    private val registerModel: RegisterModel

    init {
        this.registerModel = RegisterModel()
    }
    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()
    val user by lazy { userRepository.currentUser() }

    fun signup(registerModel: RegisterModel){
        registerModel.email = email.toString()
        registerModel.password = password.toString()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        authListener?.onStarted()

        val disposable = userRepository.register(registerModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            },{
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun goToLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}