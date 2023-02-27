package com.appointmate.login.di

import com.appointmate.login.*
import com.appointmate.login.repository.LoginShearedRepository
import com.appointmate.login.repository.LoginShearedRepositoryImp
import com.appointmate.login.repository.TokenShearedPreferences
import org.koin.dsl.module

val loginModules by lazy {
    arrayListOf(
        loginShearedRepositoryModule,
        tokenShearedPreferencesModule,
        sendSmsShearedUseCaseModule,
        verifyCodeUseCaseModule,
        getBarberTokenUseCase
    )
}

private val loginShearedRepositoryModule = module {
    single<LoginShearedRepository> { LoginShearedRepositoryImp(get()) }
}

private val tokenShearedPreferencesModule = module {
    single { TokenShearedPreferences() }
}

private val sendSmsShearedUseCaseModule = module {
    single<SendSmsUseCase> { SendSmsUseCaseImpl(get()) }
}

private val verifyCodeUseCaseModule = module {
    single<VerifyCodeUseCase> { VerifyCodeUseCaseImpl(get()) }
}

private val getBarberTokenUseCase = module {
    single<GetBarberTokenUseCase> { GetBarberTokenUseCaseImpl(get()) }
}

