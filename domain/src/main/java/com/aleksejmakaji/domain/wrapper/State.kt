package com.aleksejmakaji.domain.wrapper

import com.aleksejmakaji.domain.error.DatabaseError
import com.aleksejmakaji.domain.error.FileFinderError

sealed class State<T>
data class SuccessState<T>(val data: T? = null, val isLoading: Boolean = false) : State<T>()
data class ErrorState<T>(val error: FileFinderError) : State<T>()

fun <T : Any?> State<T>.getDataOrNull() = if (this is SuccessState) this.data else null

suspend fun <T> suspendResultWithStateCatchingDatabaseError(action: suspend () -> T): State<T> {
    return try {
        SuccessState(action.invoke())
    } catch (exception: Exception) {
        ErrorState(DatabaseError(exception))
    }
}

fun <T> resultWithStateCatchingDatabaseError(action: () -> T): State<T> {
    return try {
        SuccessState(action.invoke())
    } catch (exception: Exception) {
        ErrorState(DatabaseError(exception))
    }
}
