package com.example.mygoalskotlin.model

import java.io.Serializable

class User : Serializable {
    var name: String? = null
    var email: String? = null
    var password: String? = null

    override fun toString(): String {
        return "User [" +
                "name: ${this.name}," +
                "email: ${this.email}," +
                "password: ${this.password}" +
                "]"
    }
}