package com.kirillkuznetsov.firstapplication.ui.emailconfirmation


import androidx.lifecycle.viewModelScope
import com.kirillkuznetsov.firstapplication.data.network.response.VerificationTokenResponse
import com.kirillkuznetsov.firstapplication.data.network.response.error.VerifyRegistrationCodeErrorResponse
import com.kirillkuznetsov.firstapplication.interactor.AuthInteractor
import com.kirillkuznetsov.firstapplication.ui.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EmailConfirmationViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): BaseViewModel() {

    private val _emailConfirmationStateFlow =
        MutableStateFlow<EmailConfirmationActionState>(EmailConfirmationActionState.Pending)

    fun signUpActionStateFlow(): Flow<EmailConfirmationActionState> {
        return _emailConfirmationStateFlow.asStateFlow()
    }

    internal lateinit var firstname: String
    internal lateinit var lastname: String
    internal lateinit var nickname: String
    internal lateinit var email: String
    internal lateinit var password: String

    fun confirmVerificationCode(code: String) {
        viewModelScope.launch {
            _emailConfirmationStateFlow.emit(EmailConfirmationActionState.Loading)
            try {
                when (val response = authInteractor.verifyRegistrationCode(
                    email = email,
                    code = code,
                    phoneNumber = "testnumber"
                )) {
                    is NetworkResponse.Success -> {
                        _emailConfirmationStateFlow.emit(EmailConfirmationActionState.CreateProfileRequired)
                        val profileResponse = authInteractor.generateAuthTokensByEmailAndPersonalInfo(
                            email = email,
                            verificationToken = response.body.verificationToken,
                            firstName = firstname,
                            lastName = lastname,
                            password = password,
                        )
                    }
                    is NetworkResponse.ServerError -> {
                        _emailConfirmationStateFlow.emit(EmailConfirmationActionState.ServerError(response))
                    }
                    is NetworkResponse.NetworkError -> {
                        _emailConfirmationStateFlow.emit(EmailConfirmationActionState.NetworkError(response))
                    }
                    is NetworkResponse.UnknownError -> {
                        _emailConfirmationStateFlow.emit(EmailConfirmationActionState.UnknownError(response))
                    }
                }
            } catch (error: Throwable) {
                Timber.e(error)

                _emailConfirmationStateFlow.emit(
                    EmailConfirmationActionState.UnknownError(
                        NetworkResponse.UnknownError(
                            error
                        )
                    )
                )
            }
        }
    }

    sealed class EmailConfirmationActionState {
        object Pending : EmailConfirmationActionState()
        object Loading : EmailConfirmationActionState()
        object CreateProfileRequired : EmailConfirmationActionState()

        data class ServerError(val e: NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse>) : EmailConfirmationActionState()
        data class NetworkError(val e: NetworkResponse.NetworkError) : EmailConfirmationActionState()
        data class UnknownError(val e: NetworkResponse.UnknownError) : EmailConfirmationActionState()
    }
}