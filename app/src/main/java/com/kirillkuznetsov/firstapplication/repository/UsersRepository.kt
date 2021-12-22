package com.kirillkuznetsov.firstapplication.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.kirillkuznetsov.firstapplication.data.network.Api
import com.kirillkuznetsov.firstapplication.entity.User
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val api: Api
) {

    suspend fun getUsers(): NetworkResponse<List<User>, Unit> =
        api.getUsers()
}