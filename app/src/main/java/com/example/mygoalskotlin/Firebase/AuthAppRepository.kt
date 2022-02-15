package com.example.mygoalskotlin.Firebase

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mygoalskotlin.Register.Model.RegisterDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthAppRepository {
    private lateinit var application: Application
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var userLiveData: MutableLiveData<FirebaseUser>
    private lateinit var loggedOutLiveData: MutableLiveData<Boolean>

    fun AuthAppRepository(application: Application){
        this.application = application
        this.firebaseAuth = FirebaseAuth.getInstance()
        this.userLiveData = MutableLiveData()
        this.loggedOutLiveData = MutableLiveData()

        if (firebaseAuth.currentUser != null){
            userLiveData.postValue(firebaseAuth.currentUser)
            loggedOutLiveData.postValue(false)
        }
    }

    fun registerUserOnFirebase(registerDTO: RegisterDTO): Boolean {
        var registerSuccess: Boolean = false
        firebaseAuth.createUserWithEmailAndPassword(registerDTO.email, registerDTO.password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    userLiveData.postValue(firebaseAuth.currentUser)
                    registerSuccess = true
                }else{
                    Toast.makeText(application.applicationContext, "Não foi possível criar o usuário", Toast.LENGTH_LONG).show()
                }
            }
        return registerSuccess
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser>{
        return userLiveData
    }

    fun getLoggedOutLiveData(): MutableLiveData<Boolean>{
        return loggedOutLiveData
    }
}