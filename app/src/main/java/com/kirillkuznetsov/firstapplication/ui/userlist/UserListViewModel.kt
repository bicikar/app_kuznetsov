package com.kirillkuznetsov.firstapplication.ui.userlist

import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.kirillkuznetsov.firstapplication.entity.User
import com.kirillkuznetsov.firstapplication.interactor.UsersInteractor
import com.kirillkuznetsov.firstapplication.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {
    sealed class ViewState {
        object Loading : ViewState()
        data class Data(val userList: List<User>) : ViewState()
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: Flow<ViewState> get() = _viewState.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _viewState.emit(ViewState.Loading)
            when(val response = usersInteractor.loadUsers()){
                is NetworkResponse.Success ->{
                    _viewState.emit(ViewState.Data(response.body))
                }
                else -> {
//                    something
                }
            }
        }
    }

}