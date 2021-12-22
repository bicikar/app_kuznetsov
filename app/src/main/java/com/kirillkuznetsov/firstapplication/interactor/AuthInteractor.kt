package com.kirillkuznetsov.firstapplication.interactor


import com.haroldadmin.cnradapter.NetworkResponse
import com.kirillkuznetsov.firstapplication.data.network.response.VerificationTokenResponse
import com.kirillkuznetsov.firstapplication.data.network.response.error.CreateProfileErrorResponse
import com.kirillkuznetsov.firstapplication.data.network.response.error.SendRegistrationVerificationCodeErrorResponse
import com.kirillkuznetsov.firstapplication.data.network.response.error.SignInWithEmailErrorResponse
import com.kirillkuznetsov.firstapplication.data.network.response.error.VerifyRegistrationCodeErrorResponse
import com.kirillkuznetsov.firstapplication.entity.AuthTokens
import com.kirillkuznetsov.firstapplication.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject


class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend fun isAuthorized(): Flow<Boolean> =
        authRepository.isAuthorizedFlow()

    suspend fun signInWithEmail(
        email: String,
        password: String
    ): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse> {
        val response = authRepository.generateAuthTokensByEmail(email, password)
        when (response) {
            is NetworkResponse.Success -> {
                authRepository.saveAuthTokens(response.body)
            }
            is NetworkResponse.Error -> {
                Timber.e(response.error)
            }
        }
        return response
    }

    suspend fun signUp(
        email: String,
    ): NetworkResponse<Unit, SendRegistrationVerificationCodeErrorResponse> {
        val response = authRepository.sendRegistrationVerificationCodeRequest(email)
        when (response) {
            is NetworkResponse.Error -> {
                Timber.e(response.error)
                // TODO: Check for wrong email and profile info
            }
        }
        return response
    }

    suspend fun verifyRegistrationCode(
        email: String,
        code: String,
        phoneNumber: String
    ): NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse> {
        val response = authRepository.verifyRegistrationCode(
            code = code,
            email = email,
            phoneNumber = phoneNumber
        )
        when (response) {
            is NetworkResponse.Error -> {
                Timber.e(response.error)
            }
        }
        return response
    }

    suspend fun generateAuthTokensByEmailAndPersonalInfo(
        email: String,
        verificationToken: String,
        firstName: String,
        lastName: String,
        password: String
    ): NetworkResponse<AuthTokens, CreateProfileErrorResponse> {
        val response = authRepository.generateAuthTokensByEmailAndPersonalInfo(
            email = email,
            verificationToken = verificationToken,
            firstName = firstName,
            lastName = lastName,
            password = password
        )
        when (response) {
            is NetworkResponse.Success -> {
                authRepository.saveAuthTokens(response.body)
            }
            is NetworkResponse.Error -> {
                Timber.e(response.error)
            }
        }
        return response
    }


    suspend fun logout() {
        authRepository.saveAuthTokens(null)
    }
}