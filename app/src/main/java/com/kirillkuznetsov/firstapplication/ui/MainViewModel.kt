package com.kirillkuznetsov.firstapplication.ui

import com.kirillkuznetsov.firstapplication.interactor.AuthInteractor
import com.kirillkuznetsov.firstapplication.ui.base.BaseViewModel
import com.kirillkuznetsov.firstapplication.repository.AuthRepositoryOld
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : BaseViewModel() {

    suspend fun isAuthorizedFlow(): Flow<Boolean> = authInteractor.isAuthorizedFlow()
}