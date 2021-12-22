package com.kirillkuznetsov.firstapplication.ui.signup

import androidx.lifecycle.viewModelScope
import com.kirillkuznetsov.firstapplication.data.network.response.error.SendRegistrationVerificationCodeErrorResponse
import com.kirillkuznetsov.firstapplication.interactor.AuthInteractor
import com.kirillkuznetsov.firstapplication.ui.base.BaseViewModel
import com.kirillkuznetsov.firstapplication.ui.signin.SignInViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): BaseViewModel() {

    private val _signUpActionStateFlow =
        MutableStateFlow<SignUpActionState>(SignUpActionState.Pending)

    fun signUpActionStateFlow(): Flow<SignUpActionState> {
        return _signUpActionStateFlow.asStateFlow()
    }

    fun signUp(
        email: String,
    ) {
        viewModelScope.launch {
            _signUpActionStateFlow.emit(SignUpActionState.Loading)
            try {
                when (val response = authInteractor.signUp(
                    email = email,
                )) {
                    is NetworkResponse.Success -> {
                        _signUpActionStateFlow.emit(SignUpActionState.EmailConfirmationRequired)
                    }
                    is NetworkResponse.ServerError -> {
                        _signUpActionStateFlow.emit(SignUpActionState.ServerError(response))
                    }
                    is NetworkResponse.NetworkError -> {
                        _signUpActionStateFlow.emit(SignUpActionState.NetworkError(response))
                    }
                    is NetworkResponse.UnknownError -> {
                        _signUpActionStateFlow.emit(SignUpActionState.UnknownError(response))
                    }
                }
            } catch (error: Throwable) {
                Timber.e(error)

                _signUpActionStateFlow.emit(
                    SignUpActionState.UnknownError(
                        NetworkResponse.UnknownError(
                            error
                        )
                    )
                )
            }
        }
    }

    sealed class SignUpActionState {
        object Pending : SignUpActionState()
        object Loading : SignUpActionState()
        object EmailConfirmationRequired : SignUpActionState()

        data class ServerError(val e: NetworkResponse.ServerError<SendRegistrationVerificationCodeErrorResponse>) : SignUpActionState()
        data class NetworkError(val e: NetworkResponse.NetworkError) : SignUpActionState()
        data class UnknownError(val e: NetworkResponse.UnknownError) : SignUpActionState()
    }
}