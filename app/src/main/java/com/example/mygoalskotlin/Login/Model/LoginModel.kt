package com.example.mygoalskotlin.Login.Model

class LoginModel {
    private var email: String
        get() {
            return this.email
        }
        set(email: String) {
            this.email = email
        }

    private var password: String
        get() {
            return this.password
        }
        set(password) {
            this.password = password
        }

}