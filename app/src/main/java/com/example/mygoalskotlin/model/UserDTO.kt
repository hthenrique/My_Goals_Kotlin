package com.example.mygoalskotlin.model

import com.example.mygoalskotlin.Utils.Validator
import java.io.Serializable

class UserDTO : Serializable {
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