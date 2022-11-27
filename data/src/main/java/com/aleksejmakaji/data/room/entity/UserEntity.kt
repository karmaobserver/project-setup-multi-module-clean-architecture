package com.aleksejmakaji.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Int = 0,
    val firstName: String,
    val lastName: String
)
