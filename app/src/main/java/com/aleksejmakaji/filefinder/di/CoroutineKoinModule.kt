package com.aleksejmakaji.filefinder.di

import com.aleksejmakaji.filefinder.di.enum.DispatcherEnum
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Coroutine module is used to inject Coroutines dispatchers so it can be more easily tested
 */
val coroutineKoinModule = module {

    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    fun provideMainDispatchers(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    fun provideDefaultDispatchers(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    fun provideApplicationCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

    single(named(DispatcherEnum.IO)) { provideIODispatcher() }

    single(named(DispatcherEnum.MAIN)) { provideMainDispatchers() }

    single(named(DispatcherEnum.DEFAULT)) { provideDefaultDispatchers() }

    singleOf(::provideApplicationCoroutineScope)
}
