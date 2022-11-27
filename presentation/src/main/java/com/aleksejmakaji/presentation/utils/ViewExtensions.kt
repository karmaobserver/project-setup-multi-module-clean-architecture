package com.aleksejmakaji.presentation.utils

fun Any?.isNull(onNull: () -> Unit) {
    if (this == null) {
        onNull.invoke()
    }
}
