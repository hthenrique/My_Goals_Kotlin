package com.example.mygoalskotlin.Firebase

import android.annotation.SuppressLint
import com.example.mygoalskotlin.Login.Model.LoginModel
import com.example.mygoalskotlin.Register.Model.RegisterModel
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable

class FirebaseSource {
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    @SuppressLint("CheckResult")
    fun login(loginModel: LoginModel): Boolean{
        var success: Boolean = false
        Completable.create{ emitter ->
            firebaseAuth.signInWithEmailAndPassword(loginModel.email, loginModel.password)
                .addOnCompleteListener {
                    if (!emitter.isDisposed){
                        if (it.isSuccessful) {
                            success = true
                            emitter.onComplete()
                        }
                        else {
                            success = false
                            emitter.onError(it.exception!!)
                        }
                    }
                }
        }
        return success
    }

    @SuppressLint("CheckResult")
    fun register(registerModel: RegisterModel): Boolean{
        var success: Boolean = false
        Completable.create{ emitter ->
            firebaseAuth.signInWithEmailAndPassword(registerModel.email, registerModel.password)
                .addOnCompleteListener {
                    if (!emitter.isDisposed){
                        if (it.isSuccessful) {
                            success = true
                            emitter.onComplete()
                        }
                        else {
                            success = false
                            emitter.onError(it.exception!!)
                        }
                    }
                }
        }
        return success
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}