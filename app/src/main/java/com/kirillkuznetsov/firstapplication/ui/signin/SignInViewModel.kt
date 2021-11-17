package com.kirillkuznetsov.firstapplication.ui.signin

import androidx.lifecycle.viewModelScope
import com.kirillkuznetsov.firstapplication.ui.base.BaseViewModel
import com.kirillkuznetsov.firstapplication.repository.AuthRepository
import kotlinx.coroutines.launch

class SignInViewModel : BaseViewModel() {
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            AuthRepository.signIn(email, password)
        }
    }
}