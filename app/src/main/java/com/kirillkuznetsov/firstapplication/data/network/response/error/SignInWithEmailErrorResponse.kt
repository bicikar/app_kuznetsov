package com.kirillkuznetsov.firstapplication.data.network.response.error

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.kirillkuznetsov.firstapplication.entity.Error

@JsonClass(generateAdapter = true)
data class SignInWithEmailErrorResponse(
    @Json(name = "non_field_errors") val nonFieldErrors: List<Error>?,
    @Json(name = "email") val email: List<Error>?,
    @Json(name = "password") val password: List<Error>?
)