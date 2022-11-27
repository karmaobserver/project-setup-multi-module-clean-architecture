package com.aleksejmakaji.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aleksejmakaji.data.room.dao.UserDao
import com.aleksejmakaji.data.room.entity.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false,
)
abstract class FileFinderDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
