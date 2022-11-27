package com.aleksejmakaji.domain.usecase

/**
 * UseCase should be used when we do not have any suspend function usage in the invoke method.
 */
abstract class UseCase<in Input, Output> {
    abstract fun invoke(input: Input): Output
}

/**
 * UseCaseSuspend should be used when we have suspend function usage in the invoke method.
 */
abstract class UseCaseSuspend<in Input, Output> {
    abstract suspend fun invoke(input: Input): Output
}
