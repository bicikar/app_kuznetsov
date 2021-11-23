package com.kirillkuznetsov.firstapplication.ui

import com.kirillkuznetsov.firstapplication.ui.base.BaseViewModel
import com.kirillkuznetsov.firstapplication.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel : BaseViewModel() {

    val isAuthorizedFlow: Flow<Boolean> = AuthRepository.isAuthorizedFlow
}