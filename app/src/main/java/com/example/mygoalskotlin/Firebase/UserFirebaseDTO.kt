package com.example.mygoalskotlin.Firebase

import java.io.Serializable

class UserFirebaseDTO: Serializable {

    var uid: String? = null
    var name: String? = null
    var email: String? = null

    var isAuthenticated = false

    var isCreated = false

    fun UserFirebaseDTO(uid: String, name: String, email: String){
        this.uid = uid
        this.name = name
        this.email = email
    }

}