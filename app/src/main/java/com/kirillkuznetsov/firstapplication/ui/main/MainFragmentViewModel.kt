package com.kirillkuznetsov.firstapplication.ui.main

import com.kirillkuznetsov.firstapplication.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MainFragmentViewModel : BaseViewModel() {
    val isAuthorizedFlow: Flow<Boolean> = MutableStateFlow(false)
}