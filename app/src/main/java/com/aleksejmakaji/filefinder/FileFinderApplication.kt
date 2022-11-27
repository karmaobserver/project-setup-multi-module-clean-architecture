package com.aleksejmakaji.filefinder

import android.app.Application
import com.aleksejmakaji.domain.di.domainKoinModule
import com.aleksejmakaji.filefinder.di.appKoinModule
import com.aleksejmakaji.filefinder.di.coroutineKoinModule
import com.aleksejmakaji.filefinder.di.dataKoinModule
import com.aleksejmakaji.filefinder.di.presentationKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FileFinderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FileFinderApplication)
            modules(
                listOf(
                    appKoinModule,
                    coroutineKoinModule,
                    dataKoinModule,
                    domainKoinModule,
                    presentationKoinModule
                )
            )
        }
    }
}
