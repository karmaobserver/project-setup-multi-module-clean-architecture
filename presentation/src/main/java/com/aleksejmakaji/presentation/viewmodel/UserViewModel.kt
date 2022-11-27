package com.aleksejmakaji.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aleksejmakaji.domain.model.UserModel
import com.aleksejmakaji.domain.usecase.GetUserUseCase
import com.aleksejmakaji.domain.usecase.SaveUserUseCase
import com.aleksejmakaji.domain.wrapper.State
import com.aleksejmakaji.domain.wrapper.SuccessState
import com.aleksejmakaji.presentation.common.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
) : BaseViewModel() {

    private val _saveUser = MutableSharedFlow<State<Long>>(replay = 0)
    val saveUser = _saveUser.asSharedFlow()

    val getUser = getUserUseCase.invoke(Unit).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(FLOW_STOP_TIMEOUT_MILLISECONDS),
        initialValue = SuccessState(isLoading = true)
    )

    fun saveUser(userModel: UserModel) = viewModelScope.launch {
        _saveUser.emit(saveUserUseCase.invoke(userModel))
    }
}
