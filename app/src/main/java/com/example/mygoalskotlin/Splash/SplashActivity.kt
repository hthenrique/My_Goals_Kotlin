package com.example.mygoalskotlin.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.mygoalskotlin.Firebase.FirebaseSource
import com.example.mygoalskotlin.Login.View.LoginActivity
import com.example.mygoalskotlin.R
import com.example.mygoalskotlin.Utils.MessagesConstants
import com.example.mygoalskotlin.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson

class SplashActivity : AppCompatActivity() {
    private val gson: Gson = Gson()
    private var user: User? = User()
    private val firebaseSource: FirebaseSource = FirebaseSource()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sharedPrefs = getPreferences(MODE_PRIVATE)

        val recoverUser: String? = sharedPrefs.getString("UserSaved", "")
        user = gson.fromJson(recoverUser, User::class.java)

        Handler().postDelayed({
            val startLogin = Intent(this, LoginActivity::class.java)
            startActivity(startLogin)
            finish()
        },3000)

        /*if (user != null){
            val startMain  = Intent(this, LoginActivity::class.java)
            user?.email?.let {
                user?.password?.let { it1 ->
                    firebaseAuth?.signInWithEmailAndPassword(it, it1)
                        ?.addOnCompleteListener {
                            if (it.isSuccessful){
                                startActivity(startMain)
                                this.finish()
                            }
                        }
                }
            }
        }*/
    }
}
