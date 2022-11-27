package com.aleksejmakaji.domain.usecase

import com.aleksejmakaji.domain.model.UserModel
import com.aleksejmakaji.domain.repository.UserRepositoryInterface
import com.aleksejmakaji.domain.wrapper.State

class SaveUserUseCase(
    private val userRepository: UserRepositoryInterface
) : UseCaseSuspend<UserModel, State<Long>>() {

    override suspend fun invoke(input: UserModel): State<Long> {
        return userRepository.save(input)
    }
}
