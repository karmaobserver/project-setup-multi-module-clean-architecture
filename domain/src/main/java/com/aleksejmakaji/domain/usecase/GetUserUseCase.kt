package com.aleksejmakaji.domain.usecase

import com.aleksejmakaji.domain.model.UserModel
import com.aleksejmakaji.domain.repository.UserRepositoryInterface
import com.aleksejmakaji.domain.wrapper.State
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val userRepository: UserRepositoryInterface
) : UseCase<Unit, Flow<State<UserModel?>>>() {
    override operator fun invoke(input: Unit): Flow<State<UserModel?>> {
        return userRepository.get()
    }
}
