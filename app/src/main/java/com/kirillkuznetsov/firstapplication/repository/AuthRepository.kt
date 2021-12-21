package com.kirillkuznetsov.firstapplication.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.kirillkuznetsov.firstapplication.Api
import com.kirillkuznetsov.firstapplication.data.network.request.CreateProfileRequest
import com.kirillkuznetsov.firstapplication.data.network.request.RefreshAuthTokensRequest
import com.kirillkuznetsov.firstapplication.data.network.request.SignInWithEmailRequest
import com.kirillkuznetsov.firstapplication.data.network.response.error.CreateProfileErrorResponse
import com.kirillkuznetsov.firstapplication.data.network.response.error.RefreshAuthTokensErrorResponse
import com.kirillkuznetsov.firstapplication.data.network.response.error.SignInWithEmailErrorResponse
import com.kirillkuznetsov.firstapplication.data.persistent.LocalKeyValueStorage
import com.kirillkuznetsov.firstapplication.entity.AuthTokens
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: Api,
    private val localKeyValueStorage: LocalKeyValueStorage,
    externalCoroutineScope: CoroutineScope,
    private val ioDispatcher: CoroutineDispatcher
) {

    private val authTokensFlow: Deferred<MutableStateFlow<AuthTokens?>> =
        externalCoroutineScope.async(context = ioDispatcher, start = CoroutineStart.LAZY) {
            Timber.d("Initializing auth tokens flow.")
            MutableStateFlow(
                localKeyValueStorage.authTokens
            )
        }

    suspend fun getAuthTokensFlow(): StateFlow<AuthTokens?> {
        return authTokensFlow.await().asStateFlow()
    }

    /**
     * @param authTokens active auth tokens which must be used for signing all requests
     */
    suspend fun saveAuthTokens(authTokens: AuthTokens?) {
        withContext(ioDispatcher) {
            Timber.d("Persist auth tokens $authTokens.")
            localKeyValueStorage.authTokens = authTokens
        }
        Timber.d("Emit auth tokens $authTokens.")
        authTokensFlow.await().emit(authTokens)
    }

    /**
     * @return whether active access tokens are authorized or not
     */
    suspend fun isAuthorizedFlow(): Flow<Boolean> {
        return authTokensFlow
            .await()
            .asStateFlow()
            .map { it != null }
    }

    suspend fun generateAuthTokensByEmail(
        email: String,
        password: String
    ): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse> {
        return api.signInWithEmail(SignInWithEmailRequest(email, password))
    }

    /**
     * Creates a user account in the system as a side effect.
     * @return access tokens with higher permissions for the new registered user
     */
    suspend fun generateAuthTokensByEmailAndPersonalInfo(
        email: String,
        verificationToken: String,
        firstName: String,
        lastName: String,
        password: String
    ): NetworkResponse<AuthTokens, CreateProfileErrorResponse> {
        return api.createProfile(
            CreateProfileRequest(
                verificationToken,
                firstName,
                lastName,
                email,
                password
            )
        )
    }

    suspend fun generateRefreshedAuthTokens(refreshToken: String): NetworkResponse<AuthTokens, RefreshAuthTokensErrorResponse> {
        return api.refreshAuthTokens(RefreshAuthTokensRequest(refreshToken))
    }
}