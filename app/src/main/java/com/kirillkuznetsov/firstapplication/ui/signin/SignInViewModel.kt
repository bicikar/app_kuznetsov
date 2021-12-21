package com.kirillkuznetsov.firstapplication.ui.signin

import androidx.lifecycle.viewModelScope
import com.kirillkuznetsov.firstapplication.ui.base.BaseViewModel
import com.kirillkuznetsov.firstapplication.repository.AuthRepositoryOld
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel : BaseViewModel() {
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            AuthRepositoryOld.signIn(email, password)
        }
    }
}