package com.example.mygoalskotlin.Firebase

import com.example.mygoalskotlin.Login.Model.LoginModel
import com.example.mygoalskotlin.Register.Model.RegisterModel
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable

class FirebaseSource {
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun login(loginModel: LoginModel) = Completable.create{ emitter ->
        firebaseAuth.signInWithEmailAndPassword(loginModel.email, loginModel.password)
            .addOnCompleteListener {
                if (!emitter.isDisposed){
                    if (it.isSuccessful)
                        emitter.onComplete()
                    else
                        emitter.onError(it.exception!!)
                }
            }

    }

    fun register(loginModel: RegisterModel) = Completable.create{ emitter ->
        firebaseAuth.signInWithEmailAndPassword(loginModel.email, loginModel.password)
            .addOnCompleteListener {
                if (!emitter.isDisposed){
                    if (it.isSuccessful)
                        emitter.onComplete()
                    else
                        emitter.onError(it.exception!!)
                }
            }

    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}