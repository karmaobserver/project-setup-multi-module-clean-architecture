package com.aleksejmakaji.domain.error

sealed interface FileFinderError

// IOErrors
sealed class IOError() : FileFinderError
data class DatabaseError(val throwable: Throwable) : IOError()
data class NetworkError(val throwable: Throwable) : IOError()

// UIErrors
sealed class UIError() : FileFinderError
data class SomeSpecificUIError(val resourceId: Int) : UIError()
