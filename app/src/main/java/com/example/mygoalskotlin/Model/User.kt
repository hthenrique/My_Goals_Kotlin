package com.example.mygoalskotlin.Model

import java.io.Serializable

open class User : Serializable {

    var uid: String? = null
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var position: String? = null
    var goals: Int? = 0
    var matches: Int? = 0

    override fun toString(): String {
        return "User [" +
                "name: ${this.name}," +
                "email: ${this.email}," +
                "position: ${this.position}" +
                "]"
    }
}