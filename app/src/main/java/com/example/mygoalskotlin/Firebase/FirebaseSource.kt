package com.example.mygoalskotlin.Firebase

import android.annotation.SuppressLint
import com.example.mygoalskotlin.Login.Model.Login
import com.example.mygoalskotlin.Register.Model.RegisterModel
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable

class FirebaseSource {
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    @SuppressLint("CheckResult")
    fun login(login: Login): Boolean{
        var success: Boolean = false
        Completable.create{ emitter ->
            firebaseAuth.signInWithEmailAndPassword(login.email, login.password)
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
    fun register(loginModel: RegisterModel): Boolean{
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

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}