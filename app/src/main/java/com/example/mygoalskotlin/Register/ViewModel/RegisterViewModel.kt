package com.example.mygoalskotlin.Register.ViewModel

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygoalskotlin.Firebase.AuthAppRepository
import com.example.mygoalskotlin.Firebase.AuthListener
import com.example.mygoalskotlin.Login.View.LoginActivity
import com.example.mygoalskotlin.Register.Model.RegisterDTO
import com.google.firebase.auth.FirebaseUser
import io.reactivex.disposables.CompositeDisposable


class RegisterViewModel(@NonNull application: Application): ViewModel() {
    private var authAppRepository: AuthAppRepository = AuthAppRepository(application)
    private var userLiveData: MutableLiveData<FirebaseUser> = authAppRepository.getUserLiveData()

    private var email:String? = null
    private var password:String? = null

    var authListener: AuthListener? = null

    fun register(registerDTO: RegisterDTO){
        authAppRepository.registerUserOnFirebase(registerDTO)
    }
}