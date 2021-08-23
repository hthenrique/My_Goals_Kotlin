package com.example.mygoalskotlin.Firebase

import java.io.Serializable

class User: Serializable {

    var uid: String? = null
    var name: String? = null
    var email: String? = null

    var isAuthenticated = false

    var isNew = false
    var isCreated = false

    class User () {}

    fun User(uid: String, name: String, email: String){
        this.uid = uid
        this.name = name
        this.email = email
    }

}