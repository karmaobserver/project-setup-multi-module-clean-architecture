package com.aleksejmakaji.data.repository

import com.aleksejmakaji.data.repository.source.UserLocalDataSource
import com.aleksejmakaji.domain.model.UserModel
import com.aleksejmakaji.domain.repository.UserRepositoryInterface
import com.aleksejmakaji.domain.wrapper.State
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userLocalDataSource: UserLocalDataSource) :
    UserRepositoryInterface {

    override fun get(): Flow<State<UserModel?>> = userLocalDataSource.getUser()

    override suspend fun save(userModel: UserModel): State<Long> =
        userLocalDataSource.save(userModel)
}
