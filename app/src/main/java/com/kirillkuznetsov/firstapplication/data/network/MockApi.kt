package com.kirillkuznetsov.firstapplication.data.network

import com.haroldadmin.cnradapter.NetworkResponse
import com.kirillkuznetsov.firstapplication.data.network.request.CreateProfileRequest
import com.kirillkuznetsov.firstapplication.data.network.request.RefreshAuthTokensRequest
import com.kirillkuznetsov.firstapplication.data.network.request.SignInWithEmailRequest
import com.kirillkuznetsov.firstapplication.data.network.response.VerificationTokenResponse
import com.kirillkuznetsov.firstapplication.data.network.response.error.*
import com.kirillkuznetsov.firstapplication.entity.AuthTokens
import com.kirillkuznetsov.firstapplication.entity.Post
import com.kirillkuznetsov.firstapplication.entity.User

class MockApi : Api {
    override suspend fun getUsers(): NetworkResponse<List<User>, Unit> {
        return NetworkResponse.Success(
            body = listOf(
                User(
                    id = 0,
                    userName = "man_with_a_knife",
                    avatarUrl = "https://memegenerator.net/img/images/13081266.jpg",
                    firstName = "Salavat",
                    lastName = "Karimov",
                    groupName = "Group A"
                ),
                User(
                    id = 1,
                    userName = "El Grapadura 1",
                    avatarUrl = "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/9b/9bfcfd85ddb1699e13e71790ba1728fc444184a4_full.jpg",
                    firstName = "El",
                    lastName = "Grapadura",
                    groupName = "Group E"
                ),
                User(
                    id = 2,
                    userName = "El Grapadura 2",
                    avatarUrl = "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/9b/9bfcfd85ddb1699e13e71790ba1728fc444184a4_full.jpg",
                    firstName = "El",
                    lastName = "Grapadura",
                    groupName = "Group E"
                ),
                User(
                    id = 3,
                    userName = "El Grapadura 3",
                    avatarUrl = "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/9b/9bfcfd85ddb1699e13e71790ba1728fc444184a4_full.jpg",
                    firstName = "El",
                    lastName = "Grapadura",
                    groupName = "Group E"
                ),
                User(
                    id = 4,
                    userName = "El Grapadura 4",
                    avatarUrl = "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/9b/9bfcfd85ddb1699e13e71790ba1728fc444184a4_full.jpg",
                    firstName = "El",
                    lastName = "Grapadura",
                    groupName = "Group E"
                ),
                User(
                    id = 5,
                    userName = "El Grapadura 5",
                    avatarUrl = "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/9b/9bfcfd85ddb1699e13e71790ba1728fc444184a4_full.jpg",
                    firstName = "El",
                    lastName = "Grapadura",
                    groupName = "Group E"
                ),
                User(
                    id = 6,
                    userName = "El Grapadura 6",
                    avatarUrl = "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/9b/9bfcfd85ddb1699e13e71790ba1728fc444184a4_full.jpg",
                    firstName = "El",
                    lastName = "Grapadura",
                    groupName = "Group E"
                )
            ),
            code = 200
        )
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
        return NetworkResponse.Success(
            Unit,
            code = 200
        )
    }

    override suspend fun verifyRegistrationCode(
        code: String,
        email: String?,
        phoneNumber: String?
    ): NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse> {
        return NetworkResponse.Success(
            VerificationTokenResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI"),
            code = 200
        )
    }

    override suspend fun createProfile(request: CreateProfileRequest): NetworkResponse<AuthTokens, CreateProfileErrorResponse> {
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

    override suspend fun getPosts(): NetworkResponse<List<Post>, Unit> {
        TODO("Not yet implemented")
    }
}