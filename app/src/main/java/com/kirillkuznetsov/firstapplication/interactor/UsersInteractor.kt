package com.kirillkuznetsov.firstapplication.interactor

import com.haroldadmin.cnradapter.NetworkResponse
import com.kirillkuznetsov.firstapplication.entity.User
import com.kirillkuznetsov.firstapplication.repository.AuthRepository
import com.kirillkuznetsov.firstapplication.repository.UsersRepository
import javax.inject.Inject

class UsersInteractor @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun loadUsers(): NetworkResponse<List<User>, Unit> =
        usersRepository.getUsers()
}