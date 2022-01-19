package com.example.mygoalskotlin.Firebase

import android.annotation.SuppressLint
import com.example.mygoalskotlin.Register.Model.RegisterModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class FirebaseSource {

    private var firebaseAuth: FirebaseAuth? = null
    private lateinit var authListener: FirebaseAuth.AuthStateListener
    private lateinit var firebaseApp: FirebaseApp

    @SuppressLint("CheckResult")
    fun register(registerModel: RegisterModel):Boolean{
        var success: Boolean = false
        if (registerModel!=null){
            firebaseAuth?.createUserWithEmailAndPassword(registerModel.email, registerModel.password)
                ?.addOnCompleteListener {
                    if (it.isSuccessful){
                        success = it.isSuccessful
                    }
                }
        }
        return success
    }

    fun logout() = firebaseAuth?.signOut()

    fun currentUser(): UserFirebase {
        val userFirebase: UserFirebase = UserFirebase()
        val firebaseUser = Firebase.auth.currentUser

        if (firebaseUser!= null){
            userFirebase.uid = firebaseUser.uid
            userFirebase.name = firebaseUser.displayName
            userFirebase.email = firebaseUser.email
            userFirebase.isAuthenticated = true
            userFirebase.isCreated = true
        }
        return userFirebase
    }
}