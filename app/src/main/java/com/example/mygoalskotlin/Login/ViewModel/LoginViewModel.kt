package com.example.mygoalskotlin.Login.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygoalskotlin.Login.Model.LoginModel

class LoginViewModel: ViewModel() {

    private var loginModel: LoginModel = LoginModel()
    private val _onUserSendForm = MutableLiveData<LoginModel>()
    val onUserSendForm: LiveData<LoginModel> = _onUserSendForm

    fun onUserRequestedToSendForm(){
        _onUserSendForm.value = loginModel
    }

    fun onUserChangedInput(loginModel: LoginModel){
        this.loginModel = loginModel
    }
}