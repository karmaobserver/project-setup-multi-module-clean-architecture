package com.aleksejmakaji.domain.di

import com.aleksejmakaji.domain.usecase.GetUserUseCase
import com.aleksejmakaji.domain.usecase.SaveUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainKoinModule = module {
    factoryOf(::GetUserUseCase)
    factoryOf(::SaveUserUseCase)
}
