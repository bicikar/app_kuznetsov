package com.kirillkuznetsov.firstapplication.data.network

import com.haroldadmin.cnradapter.NetworkResponse
import com.kirillkuznetsov.firstapplication.Api
import com.kirillkuznetsov.firstapplication.data.network.request.CreateProfileRequest
import com.kirillkuznetsov.firstapplication.data.network.request.RefreshAuthTokensRequest
import com.kirillkuznetsov.firstapplication.data.network.request.SignInWithEmailRequest
import com.kirillkuznetsov.firstapplication.data.network.response.VerificationTokenResponse
import com.kirillkuznetsov.firstapplication.data.network.response.error.*
import com.kirillkuznetsov.firstapplication.entity.AuthTokens
import com.kirillkuznetsov.firstapplication.entity.User

class MockApi : Api {
    override suspend fun getUsers(): NetworkResponse<List<User>, Unit> {
        return NetworkResponse.Success(
            body = listOf(
                User(
                    id = 7,
                    username = "michael2000",
                    firstName = "Michael",
                    lastName = "Lawson",
                    avatarUrl = "https://reqres.in/img/faces/7-image.jpg",
                    groupName = "Б10"
                )
            ), code = 200
        )
    }
}

    override suspend fun signInWithEmail(request: SignInWithEmailRequest): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse> {
        return NetworkResponse.Success(
            AuthTokens(
                accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI",
                refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI",
                accessTokenExpiration = 1640871771000,
                refreshTokenExpiration = 1640871771000,
            ),
            code = 200
        )
    }

    override suspend fun refreshAuthTokens(request: RefreshAuthTokensRequest): NetworkResponse<AuthTokens, RefreshAuthTokensErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun sendRegistrationVerificationCode(email: String): NetworkResponse<Unit, SendRegistrationVerificationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyRegistrationCode(
        code: String,
        email: String?,
        phoneNumber: String?
    ): NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun createProfile(request: CreateProfileRequest): NetworkResponse<AuthTokens, CreateProfileErrorResponse> {
        TODO("Not yet implemented")
    }
}