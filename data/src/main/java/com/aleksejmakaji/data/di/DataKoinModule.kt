package com.aleksejmakaji.filefinder.di

import android.app.Application
import androidx.room.Room
import com.aleksejmakaji.data.mapper.UserMapper
import com.aleksejmakaji.data.repository.UserRepository
import com.aleksejmakaji.data.repository.source.UserLocalDataSource
import com.aleksejmakaji.data.room.FileFinderDatabase
import com.aleksejmakaji.data.room.dao.UserDao
import com.aleksejmakaji.domain.repository.UserRepositoryInterface
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataKoinModule = module {

    fun provideDataBase(application: Application): FileFinderDatabase {
        return Room.databaseBuilder(
            application,
            FileFinderDatabase::class.java,
            "file_finder.db"
        )
//            TODO: add later database migration and remove fallbackToDestruction option
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideUserDao(fileFinderDatabase: FileFinderDatabase): UserDao {
        return fileFinderDatabase.userDao()
    }

    single { provideDataBase(androidApplication()) }
    singleOf(::provideUserDao)

    singleOf(::UserLocalDataSource)
    singleOf(::UserRepository) { bind<UserRepositoryInterface>() }
    singleOf(::UserMapper)
}
