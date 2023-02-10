package com.appointmate.login.di

import com.appointmate.login.SendSmsUseCase
import com.appointmate.login.VerifyCodeUseCase
import org.koin.dsl.module

val loginModules by lazy {
    arrayListOf(
        sendSmsUseCaseModule,
        verifyCodeUseCaseModule
    )
}
private val sendSmsUseCaseModule = module {
    single { SendSmsUseCase() }
}

private val verifyCodeUseCaseModule = module {
    single { VerifyCodeUseCase() }
}
