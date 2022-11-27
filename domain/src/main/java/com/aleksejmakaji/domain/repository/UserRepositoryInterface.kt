package com.aleksejmakaji.domain.repository

import com.aleksejmakaji.domain.model.UserModel
import com.aleksejmakaji.domain.wrapper.State
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {
    fun get(): Flow<State<UserModel?>>
    suspend fun save(userModel: UserModel): State<Long>
}
