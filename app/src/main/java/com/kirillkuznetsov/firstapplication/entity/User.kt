package com.kirillkuznetsov.firstapplication.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "avatar") val avatarUrl: String, // For example: "https://domain.com/user_1_avatar.png
    @Json(name = "first_name") val userName: String,
    @Json(name = "email") val groupName: String
)